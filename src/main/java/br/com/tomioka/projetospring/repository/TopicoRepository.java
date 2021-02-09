package br.com.tomioka.projetospring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tomioka.projetospring.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// Método automático para busca do Spring - findBy{classe1}{atributo_classe1}
	List<Topico> findByCursoNome(String nomeCurso);


//	// Outra forma, que pode diferenciar entre uma Classe e um Atributo pelo {_}
//	List<Topico> findByCurso_Nome(String nomeCurso);
//	 
//	// Outra forma, o método pode ter qualquer nome, porém é necessário inserir a query JPQL
//	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :pnomeCurso")
//	List<Topico> encontraTopicoPorNomeCurso(@Param("pnomeCurso") String nomeCurso);

}
