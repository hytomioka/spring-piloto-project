package br.com.tomioka.projetospring.config.security;

import br.com.tomioka.projetospring.modelo.Usuario;
import br.com.tomioka.projetospring.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Servico criado para autenticacao das credencias (email e senha)
@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository repo;

    public AutenticacaoService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repo.findByEmail(email);
        return usuario.orElseThrow(
                () -> new UsernameNotFoundException("Dados inválidos.")
        );
    }
}
