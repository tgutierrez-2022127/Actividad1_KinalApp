package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Producto;
import com.taylorgutierrez.kinalapp.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    // 🔹 GET - Listar todos
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos(); // ✔ tu método real
    }

    // 🔹 GET - Buscar por ID
    @GetMapping("/{id}")
    public Optional<Producto> obtener(@PathVariable Long id) {
        return productoService.buscarPorId(id); // ✔ devuelve Optional
    }

    // 🔹 POST - Crear
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // 🔹 PUT - Actualizar
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto); // ✔ ya lo tienes en service
    }

    // 🔹 DELETE - Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    // 🔹 GET - Buscar por stock
    @GetMapping("/stock/{stock}")
    public List<Producto> buscarPorStock(@PathVariable int stock) {
        return productoService.buscarPorStock(stock);
    }
}