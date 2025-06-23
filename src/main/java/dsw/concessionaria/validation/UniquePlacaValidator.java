package dsw.concessionaria.validation;

import dsw.concessionaria.dao.VeiculoDAO;
import dsw.concessionaria.domain.Veiculo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniquePlacaValidator implements ConstraintValidator<UniquePlaca, String> {

    @Autowired
    private VeiculoDAO veiculoDAO;

    @Override
    public boolean isValid(String placa, ConstraintValidatorContext context) {
        if (veiculoDAO == null || placa == null || placa.trim().isEmpty()) {
            return true; // Não valida se o DAO não foi injetado ou se o campo está vazio (outra anotação, como @NotBlank, deve cuidar disso)
        }
        // O método 'findByPlaca' precisa existir no seu VeiculoDAO
        Veiculo veiculo = veiculoDAO.findByPlaca(placa);
        return veiculo == null;
    }
}