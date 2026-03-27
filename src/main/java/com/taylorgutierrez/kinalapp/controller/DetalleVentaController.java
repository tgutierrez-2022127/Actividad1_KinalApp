package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import com.taylorgutierrez.kinalapp.service.IDetalleVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {

    private final IDetalleVentaService detalleService;

    public DetalleVentaController(IDetalleVentaService detalleService) {
        this.detalleService = detalleService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleService.listarDetalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscar(@PathVariable Long id) {
        return detalleService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalle) {
        return ResponseEntity.status(201).body(detalleService.guardar(detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!detalleService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        detalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<DetalleVenta>> buscarPorVenta(@PathVariable Long idVenta) {
        List<DetalleVenta> detalles = detalleService.buscarPorVenta(idVenta);

        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(detalles);
    }
}
