package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    // Buscar detalles por venta
    List<DetalleVenta> findByVenta_IdVenta(Long idVenta);

}