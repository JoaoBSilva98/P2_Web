package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.ProdutoEntity;


@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:4200") 
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> obterProdutoPorId(@PathVariable Long id) {
        ProdutoEntity produto = produtoService.obterProdutoPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}