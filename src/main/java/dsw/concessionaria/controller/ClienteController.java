package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.service.spec.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/cadastro"; // -> /src/main/resources/templates/cliente/cadastro.html
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        System.out.println("--- CONTROLLER: TENTANDO SALVAR CLIENTE ---");
        System.out.println("Dados recebidos do formulário: " + cliente.toString());
        if (result.hasErrors()) {
            System.out.println("--- CONTROLLER: ERROS DE VALIDAÇÃO ENCONTRADOS ---");
            // Imprime cada erro de validação no console
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "cliente/cadastro"; // Se houver erros de validação, retorna para o formulário
        }
            System.out.println("--- CONTROLLER: VALIDAÇÃO OK. CHAMANDO O SERVIÇO... ---");

        clienteService.salvar(cliente);
            System.out.println("--- CONTROLLER: SERVIÇO EXECUTADO. REDIRECIONANDO... ---");

        attr.addFlashAttribute("sucesso", "Cadastro realizado com sucesso!");
        return "redirect:/login"; // Redireciona para a página de login após o cadastro
    }
}