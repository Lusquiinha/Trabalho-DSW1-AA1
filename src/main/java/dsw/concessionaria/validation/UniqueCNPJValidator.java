package dsw.concessionaria.validation;

import dsw.concessionaria.dao.LojaDAO;
import dsw.concessionaria.domain.Loja;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

    @Autowired
    private LojaDAO lojaDAO;

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (lojaDAO == null || cnpj == null || cnpj.trim().isEmpty()) {
            return true;
        }
        
        Loja loja = lojaDAO.findByCnpj(cnpj);
        return loja == null;
    }
}