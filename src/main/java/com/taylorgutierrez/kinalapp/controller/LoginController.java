package com.taylorgutierrez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        System.out.println(" Mostrando página de login");
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password) {
        System.out.println("=== POST /login ===");
        System.out.println("Usuario: " + username);
        System.out.println("Contraseña recibida");
        return "redirect:/";
    }
}