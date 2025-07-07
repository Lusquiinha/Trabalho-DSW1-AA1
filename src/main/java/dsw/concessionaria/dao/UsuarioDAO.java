package dsw.concessionaria.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dsw.concessionaria.domain.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findById(long id);

    Usuario findByUsername(String username);

    Usuario findByEmail(String email);

}
