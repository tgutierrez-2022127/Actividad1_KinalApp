package com.taylorgutierrez.kinalapp.controller;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import com.taylorgutierrez.kinalapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario,
                                   @RequestParam("rol") String rol,
                                   Model model) {
        try {
            System.out.println("=== REGISTRO ===");
            System.out.println("Email: " + usuario.getCorreo());
            System.out.println("Rol seleccionado: " + rol);

            if (usuarioService.existePorEmail(usuario.getCorreo())) {
                model.addAttribute("error", "El correo ya está registrado");
                return "registro";
            }

            usuario.setRol(rol);
            usuarioService.registrarUsuario(usuario);

            System.out.println(" Usuario registrado exitosamente");
            return "redirect:/login?registro=success";

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "registro";
        }
    }
}