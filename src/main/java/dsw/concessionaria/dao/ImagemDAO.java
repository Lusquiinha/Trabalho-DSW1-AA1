package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemDAO extends JpaRepository<Imagem, Long> {
    
}