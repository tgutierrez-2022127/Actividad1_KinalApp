package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Cliente;
import com.taylorgutierrez.kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Service - Anotación que registra un Bean como un Bean de Spring.
 * Indica a Spring que esta clase contiene la lógica del negocio.
 * Spring la detecta automáticamente y la registra en su contenedor.
 */
@Service

/**
 * @Transactional - Por defecto todos los métodos de esta clase serán transacciones.
 * Una transacción es que puede o no ocurrir algo.
 * Si el método se ejecuta correctamente, se guardan los cambios en la BD.
 * Si ocurre un error, se deshacen todos los cambios (rollback).
 */
@Transactional
public class ClienteService implements IClienteService {

    /* private: solo accesible dentro de la clase
       ClienteRepository: Es el repositorio para acceder a la BD
       Inyección de Dependencias: Spring nos da el repositorio automáticamente
     */
    private final ClienteRepository clienteRepository;

    /**
     * Constructor: Este se ejecuta al crear el repositorio.
     * Parámetros: Spring pasa el repositorio automáticamente y a esto se le conoce
     * como Inyección de Dependencias.
     * Asignamos el repositorio a nuestra variable de clase.
     *
     * @param clienteRepository - Repositorio de clientes inyectado por Spring
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    /**
     * @Override - Indica que estamos implementando un método de la interfaz.
     * Obtiene la lista de todos los clientes registrados en la base de datos.
     *
     * @return Lista de objetos Cliente
     */
    @Override
    public List<Cliente> listarClientes() {
        // findAll() es un método de JpaRepository que trae todos los registros
        return clienteRepository.findAll();
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     * Primero valida los datos del cliente y luego lo guarda.
     *
     * @param cliente - Objeto Cliente a guardar
     * @return El cliente guardado (con su DPI asignado)
     * @throws IllegalArgumentException - Si los datos no son válidos
     */
    @Override
    public Cliente guardar(Cliente cliente) {

        if (cliente.getEstado() != 0 && cliente.getEstado() != 1) {
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    /**
     * Busca un cliente por su DPI (Documento Personal de Identificación).
     *
     * @param dpi - DPI del cliente a buscar
     * @return Optional que puede contener el cliente o estar vacío
     *
     * @Transactional(readOnly = true) - Optimiza la consulta porque solo lee,
     * no modifica datos. Esto mejora el rendimiento.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        // findById() busca por la llave primaria (que es dpiCliente)
        // Optional nos evita el NullPointerException
        return clienteRepository.findById(dpi);
    }

    /**
     * Actualiza los datos de un cliente existente.
     * Busca el cliente por DPI, si existe actualiza sus datos.
     *
     * @param dpi - DPI del cliente a actualizar
     * @param cliente - Objeto con los nuevos datos
     * @return El cliente actualizado
     * @throws RuntimeException - Si el cliente no existe
     */
    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        // Verificamos si el cliente existe
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con DPI: " + dpi);
        }

        // Aseguramos que el DPI del objeto coincida con el de la URL
        // Por seguridad, usamos el DPI de la URL, no el que viene en el JSON
        cliente.setDpiCliente(dpi);

        // Validamos los datos antes de actualizar
        validarCliente(cliente);

        // Guardamos los cambios
        return clienteRepository.save(cliente);
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param dpi - DPI del cliente a eliminar
     * @throws RuntimeException - Si el cliente no existe
     */
    @Override
    public void eliminar(String dpi) {
        // Verificamos si el cliente existe antes de eliminar
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con DPI: " + dpi);
        }
        clienteRepository.deleteById(dpi);  // Eliminamos por DPI
    }

    /**
     * Verifica si existe un cliente con el DPI especificado.
     *
     * @param dpi - DPI a verificar
     * @return true si existe, false si no
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        // existsById() verifica si existe un registro con esa llave primaria
        return clienteRepository.existsById(dpi);
    }

    /**
     * Busca clientes por su estado (Activo/Inactivo).
     *
     * @param estado - 1 para activo, 0 para inactivo
     * @return Lista de clientes que coinciden con el estado
     */
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorEstado(Integer estado) {
        return clienteRepository.findByEstado(estado);
    }


    /**
     * Valida que los datos del cliente sean correctos.
     * Este método es privado porque solo se usa dentro de esta clase.
     *
     * @param cliente - Cliente a validar
     * @throws IllegalArgumentException - Si algún campo obligatorio está vacío
     */
    private void validarCliente(Cliente cliente) {
        // Validamos que el DPI no sea nulo
        if (cliente.getDpiCliente() == null || cliente.getDpiCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El DPI es obligatorio");
        }

        // Validamos que el nombre no esté vacío
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        // Validamos que el apellido no esté vacío
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
    }


    // Estos métodos están declarados en la interfaz pero aún no implementados
    // Se pueden implementar más adelante si se necesitan

    @Override
    public List<Cliente> listaEstado(int estado) {
        // TODO: Implementar este método
        return List.of();
    }

    @Override
    public List<Cliente> listarPorEstado(int estado) {
        // TODO: Implementar este método
        return List.of();
    }
}
