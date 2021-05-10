package br.com.tomioka.projetospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/* Projeto criado a partir do spring initializr. Link:
 * https://start.spring.io/
 */

@Controller
public class HelloController {

	@RequestMapping("/")
	@ResponseBody // A resposta da requisição será composto por este método
	public String HelloWorld() {
		return "Hello World!";
	}
}
