package br.com.tomioka.projetospring.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public List<TopicoDto> topicos(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}

	@PostMapping
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
	public TopicoDetalhadoDto buscar(@PathVariable Long id) {
		Topico topico = topicoRepository.getOne(id);
		return new TopicoDetalhadoDto(topico);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form) {
		Topico topico = form.atualiza(id, topicoRepository);
		topico.setTitulo(form.getTitulo());
		topico.setMensagem(form.getMensagem());
		return ResponseEntity.ok().body(new TopicoDto(topico));
	}
	

}
