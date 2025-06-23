package dsw.concessionaria.controller;

import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.ILojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')") // Apenas usuários com papel ADMIN podem acessar
public class AdminController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ILojaService lojaService;

    @GetMapping("/home")
    public String homeAdmin() {
        return "admin/home"; // -> /src/main/resources/templates/admin/home.html
    }

    // --- Gestão de Clientes (R1) ---
    @GetMapping("/clientes")
    public String listarClientes(ModelMap model) {
        model.addAttribute("clientes", clienteService.buscarTodos());
        return "admin/cliente/lista"; // -> .../templates/admin/cliente/lista.html
    }

    @GetMapping("/clientes/excluir/{id}")
    public String excluirCliente(@PathVariable("id") Long id, RedirectAttributes attr) {
        clienteService.excluir(id);
        attr.addFlashAttribute("sucesso", "Cliente excluído com sucesso.");
        return "redirect:/admin/clientes";
    }

    // --- Gestão de Lojas (R2) ---
    @GetMapping("/lojas")
    public String listarLojas(ModelMap model) {
        model.addAttribute("lojas", lojaService.buscarTodos());
        return "admin/loja/lista"; // -> .../templates/admin/loja/lista.html
    }

    @GetMapping("/lojas/excluir/{id}")
    public String excluirLoja(@PathVariable("id") Long id, RedirectAttributes attr) {
        lojaService.excluir(id);
        attr.addFlashAttribute("sucesso", "Loja excluída com sucesso.");
        return "redirect:/admin/lojas";
    }
}