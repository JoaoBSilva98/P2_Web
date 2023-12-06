package com.example.demo.Repositories;

import com.example.demo.Entity.CestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CestaRepository extends JpaRepository<CestaEntity, Long> {
   
}