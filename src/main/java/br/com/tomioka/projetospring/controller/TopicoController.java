package br.com.tomioka.projetospring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tomioka.projetospring.controller.dto.TopicoDto;
import br.com.tomioka.projetospring.modelo.Curso;
import br.com.tomioka.projetospring.modelo.Topico;

@RestController
public class TopicoController {

	@RequestMapping("/topicos")
	public List<TopicoDto> topicos() {

		Topico topico1 = new Topico("Duvida", "Duvida Java", new Curso("Java Fundamentos", "Programação"));
		Topico topico2 = new Topico("Duvida", "Duvida Spring", new Curso("Spring Boot", "Programação"));
		Topico topico3 = new Topico("Duvida", "Duvida JPA", new Curso("Java JPA", "Programação"));
		
		
		return TopicoDto.converter(Arrays.asList(topico1, topico2, topico3));
	}

}
