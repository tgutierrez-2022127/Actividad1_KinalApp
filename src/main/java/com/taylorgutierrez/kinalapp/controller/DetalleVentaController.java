package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detalle-ventas")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public String listarTodos(Model model) {
        List<DetalleVenta> detalles = detalleVentaService.listarDetalles();
        model.addAttribute("detalles", detalles);
        return "detalle-venta";
    }

    @GetMapping("/venta/{idVenta}")
    public String listarPorVenta(@PathVariable Long idVenta, Model model) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorVenta(idVenta);
        model.addAttribute("detalles", detalles);
        model.addAttribute("idVenta", idVenta);
        return "detalle-venta";
    }
}