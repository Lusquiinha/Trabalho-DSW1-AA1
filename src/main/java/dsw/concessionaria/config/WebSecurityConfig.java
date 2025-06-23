package dsw.concessionaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Habilita o uso de @PreAuthorize
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Bean que será usado para criptografar e verificar senhas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Permite o acesso público a essas URLs
                .requestMatchers("/", "/login", "/login-error", "/clientes/cadastrar", "/clientes/salvar").permitAll()
                .requestMatchers("/css/**", "/js/**", "/image/**", "/webjars/**").permitAll()
                // Qualquer outra requisição precisa de autenticação
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Define a página de login customizada
                .loginPage("/login")
                // Define a URL para a qual o formulário de login aponta
                .defaultSuccessUrl("/", true) // Redireciona para a home após o sucesso
                .failureUrl("/login-error") // Redireciona em caso de falha
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redireciona para a home após o logout
                .permitAll()
            );

        return http.build();
    }
}