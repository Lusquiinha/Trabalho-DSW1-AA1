package dsw.concessionaria.security;

import dsw.concessionaria.dao.UsuarioDAO; // Importe o DAO
import dsw.concessionaria.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        
        // O parâmetro 'login' pode ser um e-mail OU um username
        
        // 1. Tenta buscar por e-mail primeiro
        Usuario usuario = usuarioDAO.findByEmail(login);
        
        // 2. Se não achou por e-mail, tenta buscar por username
        if (usuario == null) {
            usuario = usuarioDAO.findByUsername(login);
        }

        // 3. Se não encontrou de nenhuma das formas, lança o erro
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + login);
        }

        return new MyUserDetails(usuario);
    }
}