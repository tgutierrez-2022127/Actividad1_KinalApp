package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Producto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    @Transactional(readOnly = true)
    List<Producto> listarProductos();

    List<Producto> listaStock(int stock);

    @Transactional(readOnly = true)
    List<Producto> listarPorStock(int stock);

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Long id);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);

    boolean existePorId(Long id);

    List<Producto> buscarPorStock(int stock);

    List<Producto> listar();
}
