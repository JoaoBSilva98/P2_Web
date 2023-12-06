package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cesta")
public class CestaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;    

    @OneToMany(mappedBy = "cesta", cascade = CascadeType.ALL)
    private List<ProdutoEntity> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }
}
