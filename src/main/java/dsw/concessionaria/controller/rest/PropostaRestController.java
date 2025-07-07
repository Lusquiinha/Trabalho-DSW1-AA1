package dsw.concessionaria.controller.rest;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.IPropostaService;
import dsw.concessionaria.service.spec.IVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/propostas")
public class PropostaRestController {

    @Autowired
    private IPropostaService propostaService;

    @Autowired
    private IVeiculoService veiculoService;
    
    @Autowired
    private IClienteService clienteService;

    /**
     * Retorna a lista de propostas de um veículo específico.
     * Mapeado para: GET http://localhost:8080/api/propostas/veiculos/{id}
     */
    @GetMapping("/veiculos/{id}")
    public ResponseEntity<List<Proposta>> listarPorVeiculo(@PathVariable("id") Long id) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        if (veiculo == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o veículo não existe
        }
        
        // Esta consulta não existe no DAO ainda, vamos precisar criá-la.
        List<Proposta> propostas = propostaService.buscarTodosPorVeiculo(veiculo);
        
        if (propostas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propostas);
    }

    /**
     * Retorna a lista de propostas de um cliente específico.
     * Mapeado para: GET http://localhost:8080/api/propostas/clientes/{id}
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<Proposta>> listarPorCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o cliente não existe
        }
        
        // Este método já existe no serviço
        List<Proposta> propostas = propostaService.buscarTodosPorCliente(cliente);
        
        if (propostas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(propostas);
    }
}