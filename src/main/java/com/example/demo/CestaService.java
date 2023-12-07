package com.example.demo;

import com.example.demo.Entity.CestaEntity;
import com.example.demo.Entity.ProdutoEntity;
import com.example.demo.Repositories.CestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CestaService {

    @Autowired
    private CestaRepository cestaRepository;
    
    @Autowired
    private ProdutoService produtoService;

    public CestaEntity adicionarItemNoCarrinho(Long clienteId, Long produtoId, int quantidade) {
        ProdutoEntity produto = produtoService.obterProdutoPorId(produtoId);

        CestaEntity itemCesta = new CestaEntity();
        itemCesta.setClienteId(clienteId);
        itemCesta.setProdutoId(produtoId);
        itemCesta.setQuantidade(quantidade);

        itemCesta.setNomeProduto(produto.getNome());
        itemCesta.setValorUnitario(produto.getPreco());

        return cestaRepository.save(itemCesta);
    }

    public List<CestaEntity> buscarItensDoCarrinho(Long clienteId) {
        return cestaRepository.findByClienteId(clienteId);
    }

    public void removerItemDoCarrinho(Long clienteId, Long produtoId) {
        Optional<CestaEntity> optionalCesta = cestaRepository.findByClienteIdAndProdutoId(clienteId, produtoId);
        optionalCesta.ifPresent(cestaRepository::delete);
    }

    public CestaEntity atualizarQuantidadeNoCarrinho(Long clienteId, Long produtoId, int novaQuantidade) {
        Optional<CestaEntity> optionalCesta = cestaRepository.findByClienteIdAndProdutoId(clienteId, produtoId);

        if (optionalCesta.isPresent()) {
            CestaEntity cesta = optionalCesta.get();
            cesta.setQuantidade(novaQuantidade);
            atualizarValorTotal(cesta);
            return cestaRepository.save(cesta);
        }

        return null;
    }


    public double calcularTotalCarrinho(Long clienteId) {
        List<CestaEntity> itensCarrinho = buscarItensDoCarrinho(clienteId);
        double total = 0.0;
        
        for (CestaEntity item : itensCarrinho) {
            total += item.getValorTotal();
        }
        
        return total;
    }

    private void atualizarValorTotal(CestaEntity cesta) {
        if (cesta.getValorUnitario() != 0 && cesta.getQuantidade() != 0) {
            double valorTotal = cesta.getValorUnitario() * cesta.getQuantidade();
            cesta.setValorTotal(valorTotal);
        } else {
            cesta.setValorTotal(0);
        }
    }
}