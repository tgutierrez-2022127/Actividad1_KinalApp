package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import com.taylorgutierrez.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByCorreo(email);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.findByCorreo(email).isPresent();
    }

    // ✅ AGREGAR ESTE MÉTODO
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        System.out.println("=== REGISTRANDO USUARIO ===");
        System.out.println("Email: " + usuario.getCorreo());
        System.out.println("Rol: " + usuario.getRol());

        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);

        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USER");
        }
        if (usuario.getEstado() == null) {
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuario.setIdUsuario(id);
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}