package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    //  Método para listar todas las ventas
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    //  Método para buscar por ID
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    //  Método para verificar si existe por ID
    public boolean existePorId(Long id) {
        return ventaRepository.existsById(id);
    }

    //  Método para guardar una venta
    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    //  Método para actualizar una venta
    public Venta actualizar(Long id, Venta ventaActualizada) {
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        ventaExistente.setFecha(ventaActualizada.getFecha());
        ventaExistente.setUsuario(ventaActualizada.getUsuario());

        return ventaRepository.save(ventaExistente);
    }

    //  Método para eliminar una venta
    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

    // Método para buscar ventas por usuario
    public List<Venta> buscarPorUsuario(Long idUsuario) {
        return ventaRepository.findByUsuarioIdUsuario(idUsuario);
    }
}