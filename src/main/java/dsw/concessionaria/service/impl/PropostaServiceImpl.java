package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.PropostaDAO;
import dsw.concessionaria.service.spec.IEmailService;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Veiculo;
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

    @Autowired
    private IEmailService emailService; 

     @Override
    public void atualizarStatus(Long id, String status, String contraProposta, String linkReuniao) {
        Proposta proposta = propostaDAO.findById(id).orElseThrow(() -> new RuntimeException("Proposta não encontrada!"));

        // Atualiza o status
        if ("ACEITO".equals(status)) {
            proposta.setStatus(StatusProposta.ACEITO);
        } else if ("NAO_ACEITO".equals(status)) {
            proposta.setStatus(StatusProposta.NAO_ACEITO);
        } else {
            return; // Não faz nada se o status for inválido
        }

        propostaDAO.save(proposta);
        
        notificarCliente(proposta, contraProposta, linkReuniao);
    }

    private void notificarCliente(Proposta proposta, String contraProposta, String linkReuniao) {
        String destinatario = proposta.getCliente().getEmail();
        String assunto = "Atualização sobre sua proposta para o veículo " + proposta.getVeiculo().getModelo();
        String corpo;

        if (proposta.getStatus() == StatusProposta.ACEITO) {
            corpo = String.format(
                "Olá %s,\n\nSua proposta foi ACEITA!\n\nDetalhes para a reunião:\n%s\n\nAtenciosamente,\n%s",
                proposta.getCliente().getNome(),
                linkReuniao, // No requisito, o link deveria estar aqui, mas usamos um campo geral.
                proposta.getVeiculo().getLoja().getNome()
            );
        } else { // NÃO ACEITO
            corpo = String.format("Olá %s,\n\nSua proposta para o veículo %s infelizmente não foi aceita.", proposta.getCliente().getNome(), proposta.getVeiculo().getModelo());
            if (contraProposta != null && !contraProposta.isEmpty()) {
                corpo += "\n\nTemos uma contra-proposta para você:\n" + contraProposta; 
            }
            corpo += "\n\nAtenciosamente,\n" + proposta.getVeiculo().getLoja().getNome();
        }
        
        emailService.enviarEmail(destinatario, assunto, corpo);
    }

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
    public List<Proposta> buscarTodosPorVeiculo(Veiculo veiculo) {
        return propostaDAO.findAllByVeiculo(veiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proposta> buscarTodosPorLoja(Loja loja) {
        return propostaDAO.findAllByVeiculo_Loja(loja); 
    }
}