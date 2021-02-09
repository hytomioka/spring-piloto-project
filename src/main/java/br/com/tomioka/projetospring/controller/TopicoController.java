package br.com.tomioka.projetospring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tomioka.projetospring.modelo.Curso;
import br.com.tomioka.projetospring.modelo.Topico;

@Controller
public class TopicoController {

	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> topicos() {

		Topico topico1 = new Topico("Duvida", "Duvida Java", new Curso("Java Fundamentos", "Programação"));
		Topico topico2 = new Topico("Duvida", "Duvida Spring", new Curso("Spring Boot", "Programação"));
		Topico topico3 = new Topico("Duvida", "Duvida JPA", new Curso("Java JPA", "Programação"));

		return Arrays.asList(topico1, topico2, topico3);
	}

}
