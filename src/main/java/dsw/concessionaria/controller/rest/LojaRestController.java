package dsw.concessionaria.controller.rest;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.service.spec.ILojaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lojas")
public class LojaRestController {

    @Autowired
    private ILojaService lojaService;

    /**
     * Retorna a lista de todas as lojas.
     * [cite_start]Mapeado para: GET http://localhost:8080/api/loja
     */
    @GetMapping
    public ResponseEntity<List<Loja>> listarTodas() {
        List<Loja> lojas = lojaService.buscarTodos();
        if (lojas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(lojas);
    }

    /**
     * Retorna uma loja específica pelo seu ID.
     * [cite_start]Mapeado para: GET http://localhost:8080/api/lojas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscarPorId(@PathVariable Long id) {
        Loja loja = lojaService.buscarPorId(id);
        if (loja != null) {
            return ResponseEntity.ok(loja);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }

    /**
     * Cria uma nova loja.
     * [cite_start]Mapeado para: POST http://localhost:8080/api/lojas
     */
    @PostMapping
    public ResponseEntity<Loja> criar(@Valid @RequestBody Loja loja) {
        lojaService.salvar(loja);
        return ResponseEntity.status(201).body(loja); // Retorna 201 Created
    }

    /**
     * Atualiza uma loja existente.
     * [cite_start]Mapeado para: PUT http://localhost:8080/api/lojas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Loja> atualizar(@PathVariable Long id, @Valid @RequestBody Loja loja) {
        if (lojaService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        loja.setId(id);
        lojaService.salvar(loja);
        return ResponseEntity.ok(loja);
    }

    /**
     * Remove uma loja.
     * [cite_start]Mapeado para: DELETE http://localhost:8080/api/lojas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (lojaService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            lojaService.excluir(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (Exception e) {
            // Se não puder excluir (ex: loja com veículos), retorna Conflito
            return ResponseEntity.status(409).build();
        }
    }
}