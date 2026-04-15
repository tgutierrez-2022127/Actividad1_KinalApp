package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import com.taylorgutierrez.kinalapp.service.ClienteService;
import com.taylorgutierrez.kinalapp.service.ProductoService;
import com.taylorgutierrez.kinalapp.service.VentaService;
import com.taylorgutierrez.kinalapp.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/principal")
    public String principal() {
        return "principal";
    }

    @GetMapping("/clientes-view")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String nuevoCliente() {
        System.out.println("=== LLEGÓ A /clientes/nuevo ===");
        return "clientes-formulario";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@RequestParam String dpiCliente,
                                 @RequestParam String nombreCliente,
                                 @RequestParam String apellidoCliente,
                                 @RequestParam String direccion,
                                 RedirectAttributes redirect) {
        System.out.println("=== LLEGÓ A /clientes/guardar ===");
        System.out.println("DPI: " + dpiCliente);
        System.out.println("Nombre: " + nombreCliente);
        System.out.println("Apellido: " + apellidoCliente);
        System.out.println("Direccion: " + direccion);

        try {
            Cliente cliente = new Cliente();
            cliente.setDpiCliente(dpiCliente);
            cliente.setNombreCliente(nombreCliente);
            cliente.setApellidoCliente(apellidoCliente);
            cliente.setDireccion(direccion);
            cliente.setEstado(1);

            clienteService.guardar(cliente);
            redirect.addFlashAttribute("exito", "Cliente guardado exitosamente");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/clientes-view";
    }

    @GetMapping("/productos-view")
    public String productos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "productos";
    }

    @GetMapping("/ventas-view")
    public String ventas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas";
    }

    @GetMapping("/detalle-venta-view")
    public String detalleVenta(Model model) {
        model.addAttribute("detalles", detalleVentaService.listarDetalles());
        return "detalle-venta";
    }
}"// Actualizacion 1" 
