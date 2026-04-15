package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import com.taylorgutierrez.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre,
                                   @RequestParam String apellido,
                                   @RequestParam String correo,
                                   @RequestParam String password,
                                   @RequestParam String rol,
                                   Model model) {

        if (usuarioRepository.existsByCorreo(correo)) {
            model.addAttribute("error", "El correo ya esta registrado");
            return "registro";
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setEstado(1);
        usuario.setRol(rol);

        usuarioRepository.save(usuario);

        model.addAttribute("exito", "Usuario registrado exitosamente. Ahora puede iniciar sesion.");
        return "login";
    }
}