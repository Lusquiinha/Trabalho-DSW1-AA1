package dsw.concessionaria.controller.rest;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.ILojaService;
import dsw.concessionaria.service.spec.IVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoRestController {

    @Autowired
    private IVeiculoService veiculoService;

    @Autowired
    private ILojaService lojaService;

    /**
     * Retorna a lista de todos os veículos de uma loja específica.
     * Mapeado para: GET http://localhost:8080/api/veiculos/lojas/{id}
     */
    @GetMapping("/lojas/{id}")
    public ResponseEntity<List<Veiculo>> listarPorLoja(@PathVariable("id") Long id) {
        Loja loja = lojaService.buscarPorId(id);
        if (loja == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 se a loja não existe
        }
        List<Veiculo> veiculos = veiculoService.buscarPorLoja(loja);
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    /**
     * Retorna a lista de veículos filtrados por modelo.
     * Mapeado para: GET http://localhost:8080/api/veiculos/modelos/{nome}
     */
    @GetMapping("/modelos/{nome}")
    public ResponseEntity<List<Veiculo>> listarPorModelo(@PathVariable("nome") String nome) {
        List<Veiculo> veiculos = veiculoService.buscarPorModelo(nome);
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    /**
     * Cria um novo veículo para uma loja específica.
     * Mapeado para: POST http://localhost:8080/api/veiculos/lojas/{id}
     */
    @PostMapping("/lojas/{id}")
    public ResponseEntity<Veiculo> criar(@PathVariable("id") Long id, @Valid @RequestBody Veiculo veiculo) {
        Loja loja = lojaService.buscarPorId(id);
        if (loja == null) {
            
            return ResponseEntity.notFound().build();
        }
        veiculo.setLoja(loja);
        veiculoService.salvar(veiculo);
        return ResponseEntity.status(201).body(veiculo);
    }
}