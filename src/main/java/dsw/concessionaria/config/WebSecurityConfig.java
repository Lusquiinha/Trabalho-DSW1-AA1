package dsw.concessionaria.config;

import dsw.concessionaria.security.UsuarioDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(authz -> authz

                // URLs da API REST, permite acesso sem autenticação
                .requestMatchers("/api/**").permitAll() 

                // URLs Públicas
                .requestMatchers("/", "/login", "/login-error", "/error", "/acesso-negado").permitAll()
                .requestMatchers("/clientes/cadastrar", "/clientes/salvar").permitAll()
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/image/**").permitAll()

                // URLs de ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // URLs de LOJA
                .requestMatchers("/veiculos/**").hasRole("STORE")

                // URLs de CLIENTE
                // Corrigido para proteger apenas o que já existe no PropostaController
                .requestMatchers("/propostas/fazer/**", "/propostas/salvar").hasRole("CLIENT")
                
                .anyRequest().authenticated()
            )
            // Desabilita CSRF para o exemplo
            .csrf(AbstractHttpConfigurer::disable)

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/login_success", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/acesso-negado"));

        return http.build();
    }



    
}