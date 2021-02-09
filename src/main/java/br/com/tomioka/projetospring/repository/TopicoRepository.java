package br.com.tomioka.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tomioka.projetospring.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
