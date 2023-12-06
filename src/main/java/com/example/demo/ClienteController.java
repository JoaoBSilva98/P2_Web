package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.ClienteEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> buscarClientePorId(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody ClienteEntity cliente) {
        try {
            ClienteEntity novoCliente = clienteService.criarCliente(cliente);
            if (novoCliente != null) {
                return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Já existe um cliente com esse email.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o cliente. Detalhes: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> atualizarCliente(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        ClienteEntity clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listarClientes() {
        List<ClienteEntity> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteEntity> fazerLogin(@RequestBody ClienteEntity cliente) {
        ClienteEntity clienteLogado = clienteService.fazerLogin(cliente);
        if (clienteLogado != null) {
            return ResponseEntity.ok(clienteLogado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/por-email/{email}")
    public ResponseEntity<ClienteEntity> buscarClientePorEmail(@PathVariable String email) {
        ClienteEntity cliente = clienteService.buscarClientePorEmail(email);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}