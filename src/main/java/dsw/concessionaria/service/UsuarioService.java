package dsw.concessionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dsw.concessionaria.dao.UsuarioDAO;
import dsw.concessionaria.domain.Usuario;

@Service
@Transactional(readOnly = false)
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public Usuario registerUsuarios(Usuario usuario) {
        if (usuarioDAO.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        return usuarioDAO.save(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.findAll();
    }
}

