package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity criarCliente(ClienteEntity cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            return null;
        }
        return clienteRepository.save(cliente);
    }

    public ClienteEntity buscarClientePorId(Long id) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.orElse(null);
    }

    public ClienteEntity atualizarCliente(Long id, ClienteEntity cliente) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            ClienteEntity clienteExistente = clienteOptional.get();
            if (!clienteExistente.getEmail().equals(cliente.getEmail()) &&
                    clienteRepository.findByEmail(cliente.getEmail()) != null) {
                return null;
            }
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setSenha(cliente.getSenha());
            clienteExistente.setCpf(cliente.getCpf());
            clienteExistente.setTelefone(cliente.getTelefone());
            return clienteRepository.save(clienteExistente);
        } else {
            return null;
        }
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<ClienteEntity> listarClientes() {
        return clienteRepository.findAll();
    }

    public ClienteEntity fazerLogin(ClienteEntity cliente) {
        return clienteRepository.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());
    }
}