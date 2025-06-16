package dsw.concessionaria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.concessionaria.domain.Client;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long>{
    
    Client findById(long id);
    
    Client findByCpf(String cpf);

}
