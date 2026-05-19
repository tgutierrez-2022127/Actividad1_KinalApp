package com.taylorgutierrez.kinalapp.service;

import com.taylorgutierrez.kinalapp.entity.Usuario;
import com.taylorgutierrez.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        System.out.println("Usuario: " + usuario.getCorreo() + " - Rol en BD: " + usuario.getRol());

        // Spring Security necesita el rol con prefijo ROLE_
        String rolConPrefijo = "ROLE_" + usuario.getRol();
        System.out.println("Rol para Spring Security: " + rolConPrefijo);

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(rolConPrefijo)))
                .build();
    }
}