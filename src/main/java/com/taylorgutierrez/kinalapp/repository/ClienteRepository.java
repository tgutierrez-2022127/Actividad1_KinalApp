package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByEstado(int estado);
}"// Actualizacion 1" 
