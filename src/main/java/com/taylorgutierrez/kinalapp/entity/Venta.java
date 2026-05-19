package com.taylorgutierrez.kinalapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private String clienteDpi;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double total;
    private LocalDate fecha;
    private Integer estado;
    private String codigoGenerico;

    // Constructor vacío
    public Venta() {}

    // Getters y Setters
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }

    public String getClienteDpi() { return clienteDpi; }
    public void setClienteDpi(String clienteDpi) { this.clienteDpi = clienteDpi; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public String getCodigoGenerico() { return codigoGenerico; }
    public void setCodigoGenerico(String codigoGenerico) { this.codigoGenerico = codigoGenerico; }
}