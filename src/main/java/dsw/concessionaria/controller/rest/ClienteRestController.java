package dsw.concessionaria.controller.rest;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.service.spec.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    /**
     * Retorna a lista de todos os clientes.
     * Mapeado para: GET http://localhost:8080/api/clientes
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(clientes); // Retorna 200 OK com a lista de clientes
    }

    /**
     * Retorna um cliente específico pelo seu ID.
     * Mapeado para: GET http://localhost:8080/api/clientes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente); // Retorna 200 OK com o cliente encontrado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se não encontrar
    }

    /**
     * Cria um novo cliente.
     * Mapeado para: POST http://localhost:8080/api/clientes
     */
    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(cliente); // Retorna 201 Created com o cliente criado
    }

    /**
     * Atualiza um cliente existente.
     * Mapeado para: PUT http://localhost:8080/api/clientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        if (clienteService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Remove um cliente.
     * Mapeado para: DELETE http://localhost:8080/api/clientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (clienteService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content em caso de sucesso
        } catch (Exception e) {
            return ResponseEntity.status(409).build(); // Retorna 409 Conflict se não puder excluir
        }
    }
}