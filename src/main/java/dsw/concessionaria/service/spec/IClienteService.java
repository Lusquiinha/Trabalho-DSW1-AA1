package dsw.concessionaria.service.spec;

import dsw.concessionaria.domain.Cliente;
import java.util.List;

public interface IClienteService {

    void salvar(Cliente cliente);
    
    void excluir(Long id);

    Cliente buscarPorId(Long id);

    List<Cliente> buscarTodos();

    Cliente buscarPorCpf(String cpf);
}