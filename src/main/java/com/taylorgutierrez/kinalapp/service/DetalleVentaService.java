package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta guardar(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }


    public void eliminar(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}