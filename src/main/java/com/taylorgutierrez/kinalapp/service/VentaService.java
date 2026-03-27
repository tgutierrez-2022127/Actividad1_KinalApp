package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con id " + id);
        }
        venta.setIdVenta(id);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con id " + id);
        }
        ventaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return ventaRepository.existsById(id);
    }

    @Override
    public List<Venta> buscarPorUsuario(Long idUsuario) {
        return ventaRepository.findByUsuario_IdUsuario(idUsuario);
    }
}