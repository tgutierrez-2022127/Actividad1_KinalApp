package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> listarVentas();

    Venta guardar(Venta venta);

    Optional<Venta> buscarPorId(Long id);

    Venta actualizar(Long id, Venta venta);

    void eliminar(Long id);

    boolean existePorId(Long id);

    List<Venta> buscarPorUsuario(Long idUsuario);
}