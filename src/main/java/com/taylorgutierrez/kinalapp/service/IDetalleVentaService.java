package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarDetalles();

    DetalleVenta guardar(DetalleVenta detalle);

    Optional<DetalleVenta> buscarPorId(Long id);

    void eliminar(Long id);

    List<DetalleVenta> buscarPorVenta(Long idVenta);

}
