package dsw.concessionaria.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dsw.concessionaria.domain.Cliente;

@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Long>{
    
    Cliente findById(long id);
    
    Cliente findByCpf(String cpf);

}
