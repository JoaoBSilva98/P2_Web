package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<CestaEntity> cestas;
}