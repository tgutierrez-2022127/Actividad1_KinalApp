package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta guardar(Venta venta) {
        try {
            System.out.println("=== VentaService.guardar() ===");
            System.out.println("Cliente DPI: " + venta.getClienteDpi());
            System.out.println("Producto ID: " + venta.getProductoId());
            System.out.println("Cantidad: " + venta.getCantidad());
            System.out.println("Precio Unitario: " + venta.getPrecioUnitario());
            System.out.println("Total: " + venta.getTotal());

            if (venta.getFecha() == null) {
                venta.setFecha(LocalDate.now());
            }
            if (venta.getEstado() == null) {
                venta.setEstado(1);
            }

            Venta saved = ventaRepository.save(venta);
            System.out.println(" Venta guardada con ID: " + saved.getIdVenta());
            return saved;

        } catch (Exception e) {
            System.out.println(" Error en ventaService: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    //  Método actualizar - necesario para editar
    public Venta actualizar(Long id, Venta venta) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        venta.setIdVenta(id);
        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return ventaRepository.existsById(id);
    }
}