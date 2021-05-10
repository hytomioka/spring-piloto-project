package br.com.tomioka.projetospring.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
