package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.CestaEntity;
import com.example.demo.Repositories.CestaRepository;

import java.util.List;

@Service
public class CestaService {

    @Autowired
    private CestaRepository cestaRepository;

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