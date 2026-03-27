package com.taylorgutierrez.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column
    private int cantidad;

    @Column
    private double precio;

    // Relación con Venta
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    // Relación con Producto (si lo tienes)
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetalle, int cantidad, double precio, Venta venta, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precio = precio;
        this.venta = venta;
        this.producto = producto;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}