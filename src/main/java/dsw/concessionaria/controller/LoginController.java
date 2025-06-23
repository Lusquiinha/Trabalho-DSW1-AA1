package dsw.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // -> /src/main/resources/templates/login.html
    }

    @GetMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("error", "Login ou senha inválidos.");
        return "login"; // Volta para a página de login com uma mensagem de erro
    }
}