package com.example.demo.Repositories;

import com.example.demo.Entity.CestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CestaRepository extends JpaRepository<CestaEntity, Long> {
    List<CestaEntity> findByClienteId(Long clienteId);
        Optional<CestaEntity> findByClienteIdAndProdutoId(Long clienteId, Long produtoId);
}