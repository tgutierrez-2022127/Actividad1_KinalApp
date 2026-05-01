package com.taylorgutierrez.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName = "idVenta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")  // ← También corregir aquí
    private Producto producto;

    private Integer cantidad;
    private Double precio;

    // Getters y Setters
    // ...
}