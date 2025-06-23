package dsw.concessionaria.validation;

import dsw.concessionaria.dao.UsuarioDAO;
import dsw.concessionaria.domain.Usuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (usuarioDAO == null || username == null || username.trim().isEmpty()) {
            return true;
        }
        // O método findByUsername já existe no UsuarioDAO
        Usuario usuario = usuarioDAO.findByUsername(username);
        return usuario == null;
    }
}