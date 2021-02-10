package br.com.tomioka.projetospring.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tomioka.projetospring.modelo.Topico;
import br.com.tomioka.projetospring.repository.TopicoRepository;

public class AtualizaTopicoForm {

	@NotNull @NotEmpty
	private String titulo;
	@NotNull @NotEmpty
	private String mensagem;

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

	public Topico atualiza(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		return topico;
	}

}
