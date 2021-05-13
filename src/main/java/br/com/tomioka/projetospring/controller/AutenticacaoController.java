package br.com.tomioka.projetospring.controller;

import br.com.tomioka.projetospring.config.security.TokenService;
import br.com.tomioka.projetospring.controller.dto.TokenDto;
import br.com.tomioka.projetospring.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile(value = {"test", "prod"})
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginForm form) {
        // cria o usuario para consulta de autenticacao
        UsernamePasswordAuthenticationToken dadosLogin = form.converte();
        try {
            // autentica o usuario
            Authentication authenticate = authManager.authenticate(dadosLogin);
            // cria token de autenticacao
            String token = tokenService.geraToken(authenticate);

            return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
