package br.com.tomioka.projetospring.config.security;

import br.com.tomioka.projetospring.modelo.Usuario;
import br.com.tomioka.projetospring.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository repo;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repo) {
        this.tokenService = tokenService;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean tokenEhValido = tokenService.isTokenValido(token);
        if (tokenEhValido) {
            autenticaUsuario(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticaUsuario(String token) {
        Long usuarioId = tokenService.retornaUsuario(token);
        Usuario usuario = repo.findById(usuarioId).get();
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.split(" ")[1];
    }
}
