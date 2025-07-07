package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoDAO extends CrudRepository<Veiculo, Long> {

    // Método para o Requisito R6: Listagem de todos os veículos de uma loja 
    List<Veiculo> findAllByLoja(Loja loja);
    Veiculo findByPlaca(String placa);
    Veiculo findByChassi(String chassi);



    // Método para o Requisito R4: Filtrar os veículos por modelo 
    List<Veiculo> findAllByModeloContainingIgnoreCase(String modelo);
}