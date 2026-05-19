package com.taylorgutierrez.kinalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        // Rutas públicas
                        .requestMatchers("/login", "/registro", "/css/**", "/js/**").permitAll()

                        // ===== RUTAS QUE TODOS PUEDEN VER (ADMIN Y CLIENTE) =====
                        .requestMatchers("/clientes", "/productos", "/ventas", "/detalle-ventas", "/detalle-ventas/**").hasAnyRole("ADMIN", "USER")

                        // ===== RUTAS SOLO PARA ADMIN (crear, editar, eliminar) =====
                        .requestMatchers("/clientes/nuevo", "/clientes/editar/**", "/clientes/guardar", "/clientes/eliminar/**",
                                "/productos/nuevo", "/productos/editar/**", "/productos/guardar", "/productos/eliminar/**",
                                "/ventas/nuevo", "/ventas/editar/**", "/ventas/guardar", "/ventas/eliminar/**").hasRole("ADMIN")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}