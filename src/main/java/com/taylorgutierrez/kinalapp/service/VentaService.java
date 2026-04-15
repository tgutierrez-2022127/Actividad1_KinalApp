package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaService detalleVentaService;

    // Generar codigo generico unico
    private String generarCodigoGenerico() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String random = String.format("%04d", new Random().nextInt(9999));
        String codigo = "VENTA-" + fecha + "-" + random;

        while (ventaRepository.existsByCodigoGenerico(codigo)) {
            random = String.format("%04d", new Random().nextInt(9999));
            codigo = "VENTA-" + fecha + "-" + random;
        }
        return codigo;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Optional<Venta> buscarPorCodigoGenerico(String codigo) {
        return ventaRepository.findByCodigoGenerico(codigo);
    }

    public Venta guardar(Venta venta) {
        // Generar codigo si no tiene
        if (venta.getCodigoGenerico() == null || venta.getCodigoGenerico().isEmpty()) {
            venta.setCodigoGenerico(generarCodigoGenerico());
        }
        // Fecha actual si no tiene
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        // Estado por defecto
        if (venta.getEstado() == null || venta.getEstado().isEmpty()) {
            venta.setEstado("PENDIENTE");
        }
        // Calcular total
        venta.calcularTotal();

        // Guardar la venta
        Venta ventaGuardada = ventaRepository.save(venta);


        if (venta.getProducto() != null && venta.getCantidad() != null && venta.getPrecioUnitario() != null) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(venta.getCantidad());
            detalle.setPrecio(venta.getPrecioUnitario());
            detalle.setProducto(venta.getProducto());
            detalle.setVenta(ventaGuardada);
            detalleVentaService.guardar(detalle);
        }

        return ventaGuardada;
    }

    public Venta actualizar(Long id, Venta ventaActualizada) {
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        ventaExistente.setFecha(ventaActualizada.getFecha());
        ventaExistente.setTotal(ventaActualizada.getTotal());
        ventaExistente.setEstado(ventaActualizada.getEstado());
        ventaExistente.setCantidad(ventaActualizada.getCantidad());
        ventaExistente.setPrecioUnitario(ventaActualizada.getPrecioUnitario());
        ventaExistente.setCliente(ventaActualizada.getCliente());
        ventaExistente.setUsuario(ventaActualizada.getUsuario());
        ventaExistente.setProducto(ventaActualizada.getProducto());

        ventaExistente.calcularTotal();

        return ventaRepository.save(ventaExistente);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return ventaRepository.existsById(id);
    }

    public List<Venta> buscarPorEstado(String estado) {
        return ventaRepository.findByEstado(estado);
    }

    public List<Venta> buscarPorCliente(String dpiCliente) {
        return ventaRepository.findByClienteDpiCliente(dpiCliente);
    }

    public List<Venta> buscarPorUsuario(Long idUsuario) {
        return ventaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Venta> buscarPorProducto(Long idProducto) {
        return ventaRepository.findByProductoIdProducto(idProducto);
    }
}