package dsw.concessionaria.service.spec;

import dsw.concessionaria.domain.Usuario;

public interface IUsuarioService {
    
    Usuario buscarPorEmail(String email);
    
    void initAdminUser();
}