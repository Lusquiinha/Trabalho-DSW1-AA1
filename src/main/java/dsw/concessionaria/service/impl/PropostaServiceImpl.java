package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.PropostaDAO;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.enums.StatusProposta;
import dsw.concessionaria.service.spec.IPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class PropostaServiceImpl implements IPropostaService {

    @Autowired
    private PropostaDAO propostaDAO;

    @Override
    public void salvar(Proposta proposta) {
        Optional<Proposta> propostaExistente = propostaDAO.findByClienteAndVeiculoAndStatus(
                proposta.getCliente(), proposta.getVeiculo(), StatusProposta.ABERTO);

        if (propostaExistente.isPresent()) {

            throw new RuntimeException("Cliente já possui uma proposta em aberto para este veículo.");
        }
        
        proposta.setDataProposta(LocalDateTime.now()); 
        proposta.setStatus(StatusProposta.ABERTO); 
        
        propostaDAO.save(proposta);
    }

    @Override
    @Transactional(readOnly = true)
    public Proposta buscarPorId(Long id) {
        return propostaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proposta> buscarTodosPorCliente(Cliente cliente) {
        return propostaDAO.findAllByCliente(cliente); 
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proposta> buscarTodosPorLoja(Loja loja) {
        return propostaDAO.findAllByVeiculo_Loja(loja); 
    }
}