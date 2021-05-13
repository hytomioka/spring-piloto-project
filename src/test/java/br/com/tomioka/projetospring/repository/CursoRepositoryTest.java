package br.com.tomioka.projetospring.repository;

import br.com.tomioka.projetospring.modelo.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// utilizará o banco de dados configurado no arquivo pom.xml. Caso esta anotacao n seja utilizada, h2 é o padrão
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarCursoPeloNome() {
        String nomeCurso = "Spring Boot";
        persisteNovoCurso("Spring Boot", "Programação");
        Curso curso = repository.findByNome(nomeCurso);
        assertThat(curso).isNotNull();
        assertThat(nomeCurso).isEqualTo(curso.getNome());
    }

    private void persisteNovoCurso(String nomeCurso, String categoriaCurso) {
        Curso curso = new Curso();
        curso.setNome(nomeCurso);
        curso.setCategoria(categoriaCurso);
        em.persist(curso);
    }

    @Test
    public void naoDeveriaRetornarCursoPeloCasoNaoExista() {
        String nomeCurso = "Java";
        Curso curso = repository.findByNome(nomeCurso);
        assertThat(curso).isNull();
    }
}