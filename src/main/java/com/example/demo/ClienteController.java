package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.ClienteEntity;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> buscarClientePorId(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> criarCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity novoCliente = clienteService.criarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
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
}