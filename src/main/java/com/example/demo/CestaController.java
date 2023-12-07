package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.CestaEntity;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CestaController {

    @Autowired
    private CestaService cestaService;

    @GetMapping("/api/cliente/{id}")
    public ResponseEntity<CestaEntity> buscarCestaPorId(@PathVariable Long id) {
        CestaEntity cesta = cestaService.buscarCestaPorId(id);
        return ResponseEntity.ok(cesta);
    }

    @PostMapping("/api/cliente/{clienteId}/adicionar-produto/{produtoId}")
    public ResponseEntity<CestaEntity> adicionarItemNaCesta(
            @PathVariable Long clienteId, @PathVariable Long produtoId, @RequestBody int quantidade) {
        CestaEntity novaCesta = cestaService.adicionarItemNaCesta(clienteId, produtoId, quantidade);
        return new ResponseEntity<>(novaCesta, HttpStatus.CREATED);
    }

    @PutMapping("/api/cesta/{id}")
    public ResponseEntity<CestaEntity> atualizarCesta(
            @PathVariable Long id, @RequestBody CestaEntity cesta) {
        CestaEntity cestaAtualizada = cestaService.atualizarCesta(id, cesta);
        return ResponseEntity.ok(cestaAtualizada);
    }

    @DeleteMapping("/api/remover-item/{id}")
    public ResponseEntity<Void> removerItemDaCesta(@PathVariable Long id) {
        cestaService.removerItemDaCesta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/calcular-total")
    public ResponseEntity<Double> calcularTotalCarrinho() {
        double total = cestaService.calcularTotalCarrinho();
        return ResponseEntity.ok(total);
    }
}