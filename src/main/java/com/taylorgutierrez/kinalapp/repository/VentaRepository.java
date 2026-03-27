package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Buscar ventas por usuario
    List<Venta> findByUsuario_IdUsuario(Long idUsuario);

}
