package com.taylorgutierrez.kinalapp.config;

import com.taylorgutierrez.kinalapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Rutas publicas
                        .requestMatchers("/login", "/registro", "/css/**", "/js/**", "/images/**").permitAll()
                        // Solo ADMIN puede crear, editar, eliminar
                        .requestMatchers("/api/clientes/guardar", "/api/clientes/eliminar/**",
                                "/api/productos/guardar", "/api/productos/eliminar/**",
                                "/clientes/nuevo", "/productos/nuevo",
                                "/clientes/editar/**", "/productos/editar/**").hasRole("ADMIN")
                        // CLIENTE y ADMIN pueden ver
                        .requestMatchers("/api/clientes", "/api/productos", "/principal",
                                "/clientes-view", "/productos-view", "/ventas-view",
                                "/detalle-venta-view").hasAnyRole("ADMIN", "CLIENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/principal", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .userDetailsService(userDetailsService)
                .httpBasic(httpBasic -> httpBasic.init(http))
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}