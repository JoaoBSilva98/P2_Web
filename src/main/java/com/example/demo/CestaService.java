package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.CestaEntity;
import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.ProdutoEntity;
import com.example.demo.Repositories.CestaRepository;
import com.example.demo.Repositories.ProdutoRepository;
import com.example.demo.Repositories.ClienteRepository;

import java.util.List;

@Service
public class CestaService {

    @Autowired
    private CestaRepository cestaRepository;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;

    public CestaEntity adicionarItemNaCesta(Long clienteId, Long produtoId, int quantidade) {
        ClienteEntity cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));
        ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + produtoId));

        CestaEntity cesta = new CestaEntity();
        cesta.setCliente(cliente);
        cesta.setProduto(produto);
        cesta.setQuantidade(quantidade);

        return cestaRepository.save(cesta);
    }

    public void removerItemDaCesta(Long id) {
        cestaRepository.deleteById(id);
    }

    public double calcularTotalCarrinho() {
        List<CestaEntity> cestas = cestaRepository.findAll();
        double total = 0;
    
        for (CestaEntity cesta : cestas) {
            Long produtoId = cesta.getProduto().getId();
            int quantidade = cesta.getQuantidade();
            Double precoUnitario = produtoRepository.findPrecoById(produtoId);
    
            if (precoUnitario != null) {
                total += precoUnitario * quantidade;
            }
        }
    
        return total;
    }
    

    public CestaEntity buscarCestaPorId(Long id) {
        return cestaRepository.findById(id).orElse(null);
    }

    public CestaEntity criarCesta(CestaEntity cesta) {
        return cestaRepository.save(cesta);
    }

    public CestaEntity atualizarCesta(Long id, CestaEntity cesta) {
        if (cestaRepository.existsById(id)) {
            cesta.setId(id);
            return cestaRepository.save(cesta);
        } else {
            return null;
        }
    }

    public void deletarCesta(Long id) {
        cestaRepository.deleteById(id);
    }

    public List<CestaEntity> listarCestas() {
        return cestaRepository.findAll();
    }
}