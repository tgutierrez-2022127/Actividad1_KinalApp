package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    @Transactional(readOnly = true)
    List<Usuario> listarUsuarios();

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);

    boolean existePorId(Long id);

    List<Usuario> buscarPorEstado(Integer estado);

}
