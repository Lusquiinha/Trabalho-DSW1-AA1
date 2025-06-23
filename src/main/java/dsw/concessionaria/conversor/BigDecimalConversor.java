package dsw.concessionaria.conversor;

import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalConversor implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        // Remove caracteres não numéricos, exceto a vírgula
        source = source.replace(",", ".");
        return new BigDecimal(Double.parseDouble(source));
    }
}