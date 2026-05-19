package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {


    List<Venta> findByEstado(Integer estado);  // ← Antes recibía String, ahora Integer


}