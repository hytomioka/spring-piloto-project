package br.com.tomioka.projetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tomioka.projetospring.controller.dto.TopicoDto;
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
	public void cadastrar(@RequestBody TopicoForm form) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
	}

}
