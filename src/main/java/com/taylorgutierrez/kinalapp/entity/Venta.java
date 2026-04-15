package com.taylorgutierrez.kinalapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "codigo_generico", unique = true, nullable = false, length = 20)
    private String codigoGenerico;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    // Cantidad de productos en la venta
    @Column(name = "cantidad")
    private Integer cantidad;

    // Precio unitario del producto
    @Column(name = "precio_unitario")
    private Double precioUnitario;

    // Relacion con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_dpi", referencedColumnName = "dpi_cliente")
    private Cliente cliente;

    // Relacion con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    // Relacion con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto")
    private Producto producto;



    public Venta() {
    }

    public Venta(Long idVenta, String codigoGenerico, LocalDateTime fecha, Double total, String estado,
                 Integer cantidad, Double precioUnitario, Cliente cliente, Usuario usuario, Producto producto) {
        this.idVenta = idVenta;
        this.codigoGenerico = codigoGenerico;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.cliente = cliente;
        this.usuario = usuario;
        this.producto = producto;
    }



    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getCodigoGenerico() {
        return codigoGenerico;
    }

    public void setCodigoGenerico(String codigoGenerico) {
        this.codigoGenerico = codigoGenerico;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



    public void calcularTotal() {
        if (cantidad != null && precioUnitario != null) {
            this.total = cantidad * precioUnitario;
        }
    }
}
