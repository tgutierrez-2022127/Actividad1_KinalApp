package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import com.taylorgutierrez.kinalapp.entity.Producto;
import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.service.ClienteService;
import com.taylorgutierrez.kinalapp.service.ProductoService;
import com.taylorgutierrez.kinalapp.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ProductoService productoService;



    @GetMapping("/")
    public String index() {
        return "index";
    }



    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes-formulario";
    }

    @GetMapping("/clientes/editar/{dpi}")
    public String mostrarFormularioEditarCliente(@PathVariable String dpi, Model model) {
        Optional<Cliente> cliente = clienteService.buscarPorDPI(dpi);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "clientes-formulario";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{dpi}")
    public String eliminarCliente(@PathVariable String dpi) {
        if (clienteService.existePorDPI(dpi)) {
            clienteService.eliminar(dpi);
        }
        return "redirect:/clientes";
    }

    // ========== PRODUCTOS ==========

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos-formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    // ========== VENTAS ==========

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas";
    }

    @GetMapping("/ventas/nuevo")
    public String mostrarFormularioNuevoVenta(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("productos", productoService.listarProductos());
        return "ventas-formulario";
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@RequestParam String clienteDpi,
                               @RequestParam Long productoId,
                               @RequestParam Integer cantidad,
                               @RequestParam String fecha,
                               @RequestParam Integer estado,
                               @RequestParam(required = false) Double total,
                               Model model) {
        try {
            System.out.println("=== GUARDANDO VENTA ===");
            System.out.println("Cliente DPI: " + clienteDpi);
            System.out.println("Producto ID: " + productoId);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Fecha: " + fecha);
            System.out.println("Estado: " + estado);

            Venta venta = new Venta();
            venta.setClienteDpi(clienteDpi);
            venta.setProductoId(productoId);
            venta.setCantidad(cantidad);
            venta.setFecha(LocalDate.parse(fecha));
            venta.setEstado(estado);

            // Obtener el precio del producto
            Optional<Producto> producto = productoService.buscarPorId(productoId);
            if (producto.isPresent()) {
                double precioUnitario = producto.get().getPrecio();
                venta.setPrecioUnitario(precioUnitario);
                venta.setTotal(cantidad * precioUnitario);
                System.out.println("Precio unitario: " + precioUnitario);
                System.out.println("Total: " + venta.getTotal());
            } else {
                System.out.println(" Producto no encontrado con ID: " + productoId);
            }

            Venta guardada = ventaService.guardar(venta);
            System.out.println(" Venta guardada con ID: " + guardada.getIdVenta());

            return "redirect:/ventas";

        } catch (Exception e) {
            System.out.println(" ERROR: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            model.addAttribute("clientes", clienteService.listarClientes());
            model.addAttribute("productos", productoService.listarProductos());
            return "ventas-formulario";
        }
    }

    @GetMapping("/ventas/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }
}