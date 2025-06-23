package dsw.concessionaria.service.spec;

import dsw.concessionaria.domain.Loja;
import java.util.List;

public interface ILojaService {

    Loja buscarPorId(Long id);

    List<Loja> buscarTodos();

    void salvar(Loja loja);

    void excluir(Long id);
    
    Loja buscarPorCnpj(String cnpj);
}