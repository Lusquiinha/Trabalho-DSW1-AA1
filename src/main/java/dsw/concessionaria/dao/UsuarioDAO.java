package dsw.concessionaria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.concessionaria.domain.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    Usuario findById(long id);

    Usuario findByUsername(String username);

    Usuario findByEmail(String email);

}
