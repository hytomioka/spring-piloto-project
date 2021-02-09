package br.com.tomioka.projetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tomioka.projetospring.controller.dto.TopicoDto;
import br.com.tomioka.projetospring.modelo.Topico;
import br.com.tomioka.projetospring.repository.TopicoRepository;

@RestController
public class TopicoController {
	
	@Autowired
	TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	public List<TopicoDto> topicos(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}

}
