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

    public List<DetalleVenta> buscarPorVenta(Long idVenta) {
        return detalleVentaRepository.findByIdVenta(idVenta);
    }
}