package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Repositories.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoEntity> obterTodosOsPedidos() {
        return pedidoRepository.findAll();
    }
}
