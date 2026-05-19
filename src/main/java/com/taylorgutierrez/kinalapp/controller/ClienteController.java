package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import com.taylorgutierrez.kinalapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarPorDpi(@PathVariable String dpi) {
        Optional<Cliente> cliente = clienteService.buscarPorDPI(dpi);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(cliente));
    }

    @PutMapping("/{dpi}")
    public ResponseEntity<Cliente> actualizar(@PathVariable String dpi, @RequestBody Cliente cliente) {
        if (!clienteService.existePorDPI(dpi)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setDpiCliente(dpi);
        return ResponseEntity.ok(clienteService.actualizar(dpi, cliente));
    }

    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String dpi) {
        if (!clienteService.existePorDPI(dpi)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.eliminar(dpi);
        return ResponseEntity.noContent().build();
    }
}
