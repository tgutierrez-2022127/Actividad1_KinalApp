package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByCodigoGenerico(String codigoGenerico);

    boolean existsByCodigoGenerico(String codigoGenerico);

    List<Venta> findByEstado(String estado);

    List<Venta> findByClienteDpiCliente(String dpiCliente);

    List<Venta> findByUsuarioIdUsuario(Long idUsuario);

    // Nuevo: buscar ventas por producto
    List<Venta> findByProductoIdProducto(Long idProducto);
}
