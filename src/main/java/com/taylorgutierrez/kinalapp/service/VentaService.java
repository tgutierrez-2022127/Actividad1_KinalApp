package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
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

<<<<<<< HEAD
=======
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

>>>>>>> 6a6303095c6c461622b30ba3294c02aba83013e9
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

<<<<<<< HEAD
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
=======
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
>>>>>>> 6a6303095c6c461622b30ba3294c02aba83013e9
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
<<<<<<< HEAD
=======

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
>>>>>>> 6a6303095c6c461622b30ba3294c02aba83013e9
}