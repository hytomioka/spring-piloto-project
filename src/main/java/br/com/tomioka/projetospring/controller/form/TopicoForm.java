package br.com.tomioka.projetospring.controller.form;

import br.com.tomioka.projetospring.modelo.Curso;
import br.com.tomioka.projetospring.modelo.Topico;
import br.com.tomioka.projetospring.repository.CursoRepository;

/*
 * Formulário de preenchimento do TopicoController, para cadastro utilizando método POST 
 */
public class TopicoForm {

	private String titulo;
	private String mensagem;
	private String nomeCurso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
