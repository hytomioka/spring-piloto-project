package br.com.tomioka.projetospring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

// teste end-to-end
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void deveriaRetornarStatus400QndDadosDeAutenticacaoEstiveremErrados() throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\" : \"invalido@email.com\", \"senha\" : \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}