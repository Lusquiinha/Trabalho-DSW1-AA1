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
        // Criptografa a senha antes de salvar o cliente
        String senhaCriptografada = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(senhaCriptografada);
        
        clientDAO.save(cliente);
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