package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoDAO extends JpaRepository<Veiculo, Long> {

    // Método para o Requisito R6: Listagem de todos os veículos de uma loja 
    List<Veiculo> findAllByLoja(Loja loja);
    Veiculo findByPlaca(String placa);
    Veiculo findByChassi(String chassi);

    List<Veiculo> findAllByVendidoFalse(); // Método para listar veículos não vendidos


    // Método para o Requisito R4: Filtrar os veículos por modelo 
    List<Veiculo> findAllByModeloContainingIgnoreCase(String modelo);
    List<Veiculo> findAllByVendidoFalseAndModeloContainingIgnoreCase(String modelo);
}