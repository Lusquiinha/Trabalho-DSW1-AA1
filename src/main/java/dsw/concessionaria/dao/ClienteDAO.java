package dsw.concessionaria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.concessionaria.domain.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Long>{
    
    Cliente findById(long id);
    
    Cliente findByCpf(String cpf);

}
