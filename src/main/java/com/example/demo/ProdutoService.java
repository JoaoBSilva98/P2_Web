package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.ProdutoEntity;
import com.example.demo.Repositories.ProdutoRepository;

import java.util.List;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoEntity> obterTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity obterProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public ProdutoEntity criarProduto(ProdutoEntity produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}