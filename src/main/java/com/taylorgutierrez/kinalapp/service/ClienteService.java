package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import com.taylorgutierrez.kinalapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorDPI(String dpi) {
        return clienteRepository.findById(dpi);
    }

    public Cliente guardar(Cliente cliente) {
        if (cliente.getEstado() == null || (cliente.getEstado() != 0 && cliente.getEstado() != 1)) {
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(String dpi, Cliente cliente) {
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con DPI: " + dpi);
        }
        cliente.setDpiCliente(dpi);
        return clienteRepository.save(cliente);
    }

    public void eliminar(String dpi) {
        clienteRepository.deleteById(dpi);
    }

    public boolean existePorDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }
}