package dsw.concessionaria.validation;

import dsw.concessionaria.dao.VeiculoDAO;
import dsw.concessionaria.domain.Veiculo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueChassiValidator implements ConstraintValidator<UniqueChassi, String> {

    @Autowired
    private VeiculoDAO veiculoDAO;

    @Override
    public boolean isValid(String chassi, ConstraintValidatorContext context) {
        if (veiculoDAO == null || chassi == null || chassi.trim().isEmpty()) {
            return true;
        }
        // O método 'findByChassi' precisa ser adicionado ao VeiculoDAO
        Veiculo veiculo = veiculoDAO.findByChassi(chassi);
        return veiculo == null;
    }
}