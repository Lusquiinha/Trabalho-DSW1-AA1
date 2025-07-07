package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Imagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemDAO extends CrudRepository<Imagem, Long> {
    
}