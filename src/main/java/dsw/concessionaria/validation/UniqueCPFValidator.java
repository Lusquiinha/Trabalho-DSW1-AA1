package dsw.concessionaria.validation;

import dsw.concessionaria.dao.ClienteDAO;
import dsw.concessionaria.domain.Cliente;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

    @Autowired
    private ClienteDAO clientDAO;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (clientDAO == null || cpf == null || cpf.trim().isEmpty()) {
            return true;
        }
        // O método findByCpf já existe no ClientDAO
        Cliente cliente = clientDAO.findByCpf(cpf);
        return cliente == null;
    }
}