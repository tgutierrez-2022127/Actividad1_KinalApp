package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.service.ClienteService;
import com.taylorgutierrez.kinalapp.service.ProductoService;
import com.taylorgutierrez.kinalapp.service.VentaService;
import com.taylorgutierrez.kinalapp.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/clientes-view")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "clientes";
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
}