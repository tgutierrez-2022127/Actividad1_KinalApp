package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new RuntimeException("Detalle no encontrado con id " + id);
        }
        detalleVentaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> buscarPorVenta(Long idVenta) {
        return detalleVentaRepository.findByVenta_IdVenta(idVenta);
    }
}