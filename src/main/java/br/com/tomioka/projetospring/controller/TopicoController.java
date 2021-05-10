package br.com.tomioka.projetospring.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tomioka.projetospring.controller.dto.TopicoDetalhadoDto;
import br.com.tomioka.projetospring.controller.dto.TopicoDto;
import br.com.tomioka.projetospring.controller.form.AtualizaTopicoForm;
import br.com.tomioka.projetospring.controller.form.TopicoForm;
import br.com.tomioka.projetospring.modelo.Topico;
import br.com.tomioka.projetospring.repository.CursoRepository;
import br.com.tomioka.projetospring.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaTopicos")
    public Page<TopicoDto> topicos(@RequestParam(required = false) String nomeCurso,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                           Pageable paginacao) {

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNomeIgnoreCase(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        // retorna o status 201 e o corpo da requisicao
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    /*
     * Busca não funciona com novos topicos criado pelo método cadastrar, devido ao
     * objeto salvo ser do tipo TopicoDto, não TopicoDetalhadoDto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhadoDto> buscar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok().body(new TopicoDetalhadoDto(topico.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form) {
        Optional<Topico> opt = topicoRepository.findById(id);
        if (opt.isPresent()) {
            Topico topico = form.atualiza(id, topicoRepository);
            topico.setTitulo(form.getTitulo());
            topico.setMensagem(form.getMensagem());
            return ResponseEntity.ok().body(new TopicoDto(topico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Topico> opt = topicoRepository.findById(id);
        if (opt.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
