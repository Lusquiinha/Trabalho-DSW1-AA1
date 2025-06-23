package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaDAO extends JpaRepository<Loja, Long> {

    Loja findByCnpj(String cnpj);

    List<Loja> findAllByNome(String nome);
}