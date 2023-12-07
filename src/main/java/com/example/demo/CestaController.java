package com.example.demo;

import com.example.demo.Entity.CestaEntity;
import com.example.demo.Entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cesta")
public class CestaController {

    @Autowired
    private CestaService cestaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<CestaEntity>> buscarItensDoCarrinho(@PathVariable Long id) {
        List<CestaEntity> itensCarrinho = cestaService.buscarItensDoCarrinho(id);
        return ResponseEntity.ok(itensCarrinho);
    }

    @PostMapping("/cliente/{clienteId}/produto/{produtoId}")
    public ResponseEntity<Map<String, Object>> adicionarItemNaCesta(
            @PathVariable Long clienteId, @PathVariable Long produtoId, @RequestBody int quantidade) {
        CestaEntity novaCesta = cestaService.adicionarItemNoCarrinho(clienteId, produtoId, quantidade);

        ProdutoEntity produto = produtoService.obterProdutoPorId(produtoId);

        Map<String, Object> response = new HashMap<>();
        response.put("cesta", novaCesta);
        response.put("produto", produto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{clienteId}/atualizar-quantidade/{produtoId}")
    public ResponseEntity<CestaEntity> atualizarQuantidade(
            @PathVariable Long clienteId, @PathVariable Long produtoId, @RequestBody int novaQuantidade) {
        CestaEntity cestaAtualizada = cestaService.atualizarQuantidadeNoCarrinho(clienteId, produtoId, novaQuantidade);
        if (cestaAtualizada != null) {
            return ResponseEntity.ok(cestaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover-item/{clienteId}/{produtoId}")
    public ResponseEntity<Void> removerItemDaCesta(
            @PathVariable Long clienteId, @PathVariable Long produtoId) {
        cestaService.removerItemDoCarrinho(clienteId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calcular-total/{clienteId}")
    public ResponseEntity<Double> calcularTotalCarrinho(@PathVariable Long clienteId) {
        double total = cestaService.calcularTotalCarrinho(clienteId);
        return ResponseEntity.ok(total);
    }
}