package com.taylorgutierrez.kinalapp.repository;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    List<Usuario> findByEstado(Integer estado);

    Optional<Usuario> findByCorreo(String correo);

}
