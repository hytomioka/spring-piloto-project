package br.com.tomioka.projetospring.controller.dto;

import java.time.LocalDateTime;

import br.com.tomioka.projetospring.modelo.Resposta;

public class RespostaDto {
	
	private Long id;
	private String mensagem;
	private String nomeUsuario;
	private LocalDateTime dataCriacao;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeUsuario = resposta.getAutor().getNome();
		this.dataCriacao = resposta.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
}
