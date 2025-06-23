package dsw.concessionaria.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login_success")
    public String loginSuccess(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Se o usuário tem o papel de ADMIN, redireciona para o painel de admin.
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return "redirect:/admin/home";
            }
            // Se o usuário tem o papel de LOJA, redireciona para a lista de seus veículos.
            else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STORE"))) {
                return "redirect:/veiculos/listar";
            }
            // Para qualquer outro usuário logado (neste caso, CLIENTE), redireciona para a home.
            else {
                return "redirect:/";
            }
        }
        // Caso ocorra algum problema inesperado, redireciona para a home como fallback.
        return "redirect:/";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}