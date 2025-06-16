package dsw.concessionaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dsw.concessionaria.dao.ClientDAO;
import dsw.concessionaria.domain.Client;

@Service
@Transactional(readOnly = false)
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public Client registerClient(Client client) {
        if (clientDAO.findByCpf(client.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        return clientDAO.save(client);
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }
}
