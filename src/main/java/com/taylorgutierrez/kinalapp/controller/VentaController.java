package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Venta;
import com.taylorgutierrez.kinalapp.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        return ResponseEntity.status(201).body(ventaService.guardar(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        try {
            if (!ventaService.existePorId(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ventaService.actualizar(id, venta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!ventaService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Venta>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<Venta> ventas = ventaService.buscarPorUsuario(idUsuario);

        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ventas);
    }
}
