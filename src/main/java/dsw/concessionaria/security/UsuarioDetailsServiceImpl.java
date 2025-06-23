package dsw.concessionaria.security;

import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        return new MyUserDetails(usuario);
    }
}