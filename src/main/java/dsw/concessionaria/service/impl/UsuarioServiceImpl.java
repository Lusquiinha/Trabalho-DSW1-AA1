package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.UsuarioDAO;
import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.enums.UserRole;
import dsw.concessionaria.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public void initAdminUser() {
        // Verifica se o usuário admin já existe no banco
        if (usuarioDAO.findByEmail("admin@concessionaria.com") == null) {
            
            // Se não existir, cria um novo usuário admin
            Usuario admin = new Usuario();
            admin.setEmail("admin@concessionaria.com");
            admin.setUsername("admin"); // O campo username está no seu modelo
            admin.setPassword(passwordEncoder.encode("admin")); // Define uma senha padrão e a criptografa
            admin.setRole(UserRole.ADMIN); // Define o papel como ADMIN
            
            usuarioDAO.save(admin);
        }
    }
}