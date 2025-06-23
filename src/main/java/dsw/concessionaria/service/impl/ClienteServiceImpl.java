package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.ClienteDAO;
import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.service.spec.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteDAO clientDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
public void salvar(Cliente cliente) {

    System.out.println("Senha original recebida. ");
    String senhaCriptografada;
    if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
        senhaCriptografada = passwordEncoder.encode(cliente.getPassword());
    }
    else{
        senhaCriptografada = clientDAO.findById(cliente.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"))
                .getPassword(); // Mantém a senha existente se não for fornecida uma nova
    }
    cliente.setPassword(senhaCriptografada);
    System.out.println("Senha criptografada com sucesso.");

    try {
        System.out.println("--- SERVICE: EXECUTANDO clientDAO.save()... ---");
        clientDAO.save(cliente);
        System.out.println("--- SERVICE: CLIENTE SALVO COM SUCESSO NO BANCO! ---");
    } catch (Exception e) {
        System.err.println("--- SERVICE: OCORREU UM ERRO AO SALVAR NO BANCO! ---");
        e.printStackTrace(); // Imprime o erro completo do banco no console
    }
}

    @Override
    public void excluir(Long id) {
        clientDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clientDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return clientDAO.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorCpf(String cpf) {
        return clientDAO.findByCpf(cpf);
    }
}