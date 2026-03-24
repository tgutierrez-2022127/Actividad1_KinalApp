package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    //Interfaz: Es un contrato que dice QUÉ metodos dene tener
    // cualquier servicio de cliente, No tiene
    //implementación, solola definición de los métodos

    /*
     * readOnly = true: Lo que hace es optimizar la consulra no bloquea la BD
     */
    @Transactional(readOnly = true)
    List<Cliente> listarClientes();

    List<Cliente> listaEstado(int estado);

    @Transactional(readOnly = true)
    List<Cliente> listarPorEstado(int estado);

    //Metodo que guarda un Cliente en la BD
    Cliente guardar(Cliente cliente);
    //Parámetros - Recibe un objeto Cliente con los datos a guardar

    //Optional - Contenedor que puede o no tener un valor
    //evita el error de NullPointerException
    Optional<Cliente> buscarPorDPI(String dpi);

    //Método que actualiza un Cliente
    Cliente actualizar(String dpi, Cliente cliente);
    //Parámetros - dpi: DPI del cliente a actualizar
    //Cliente cliemye: Objeto con los datos nuevos
    //Retorna un objeto de tipo Cliente ya actualizado

    //Metodo de tipo void para eliminar a un Cliente
    //void: no retorna ningun dato
    //Elimina un Cliente por su DPI
    void eliminar(String dpi);

    //boolean - Retorna tru si existe, false si no existe
    boolean existePorDPI(String dpi);

    List<Cliente> buscarPorEstado(Integer estado);


}
