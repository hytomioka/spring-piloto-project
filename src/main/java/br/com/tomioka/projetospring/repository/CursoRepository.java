package br.com.tomioka.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tomioka.projetospring.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);
	
}
