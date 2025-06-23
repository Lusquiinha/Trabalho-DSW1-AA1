package dsw.concessionaria.service.spec;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Proposta;
import java.util.List;

public interface IPropostaService {

    void salvar(Proposta proposta);

    Proposta buscarPorId(Long id);
    
    // Requisito R7
    List<Proposta> buscarTodosPorCliente(Cliente cliente);

    // Apoio ao Requisito R8
    List<Proposta> buscarTodosPorLoja(Loja loja);
}