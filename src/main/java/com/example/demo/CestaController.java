package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.CestaEntity;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<CestaEntity> criarCesta(@RequestBody CestaEntity cesta) {
        CestaEntity novaCesta = cestaService.criarCesta(cesta);
        return new ResponseEntity<>(novaCesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CestaEntity> atualizarCesta(@PathVariable Long id, @RequestBody CestaEntity cesta) {
        CestaEntity cestaAtualizada = cestaService.atualizarCesta(id, cesta);
        return ResponseEntity.ok(cestaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCesta(@PathVariable Long id) {
        cestaService.deletarCesta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CestaEntity>> listarCestas() {
        List<CestaEntity> cestas = cestaService.listarCestas();
        return ResponseEntity.ok(cestas);
    }
}