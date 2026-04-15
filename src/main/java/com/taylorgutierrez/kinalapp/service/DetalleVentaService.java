package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public void eliminar(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}"// Actualizacion 1" 
