package dsw.concessionaria.dao;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.enums.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaDAO extends JpaRepository<Proposta, Long> {

    // Método para o Requisito R7: Listar todas as propostas de um cliente 
    List<Proposta> findAllByCliente(Cliente cliente);

    // Método para o Requisito R8: Permitir que a loja veja as propostas de seus veículos
    List<Proposta> findAllByVeiculo_Loja(Loja loja);

    List<Proposta> findAllByVeiculo(Veiculo veiculo);

    // Método de apoio para o Requisito R13: Verificar se o cliente já tem proposta em aberto para um veículo 
    Optional<Proposta> findByClienteAndVeiculoAndStatus(Cliente cliente, Veiculo veiculo, StatusProposta status);
}