package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.service.spec.IClienteService;
import dsw.concessionaria.service.spec.ILojaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ILojaService lojaService;

    @GetMapping("/home")
    public String homeAdmin() {
        return "admin/home";
    }

    // ========== Gestão de Lojas (R2) ==========

    @GetMapping("/lojas")
    public String listarLojas(ModelMap model) {
        model.addAttribute("lojas", lojaService.buscarTodos());
        return "admin/loja/lista";
    }

    @GetMapping("/lojas/cadastrar")
    public String formCadastroLoja(ModelMap model) {
        model.addAttribute("loja", new Loja());
        return "admin/loja/cadastro";
    }

    @GetMapping("/lojas/editar/{id}")
    public String formEdicaoLoja(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("loja", lojaService.buscarPorId(id));
        return "admin/loja/cadastro";
    }

    @PostMapping("/lojas/editar")
    public String editarLoja(@Valid Loja loja, BindingResult result, RedirectAttributes attr) {
        if (result.getFieldErrorCount() > 3) {
            System.out.println("Erro de validação: " + result.getFieldErrorCount());
            return "admin/loja/cadastro";
        }
        lojaService.salvar(loja);
        attr.addFlashAttribute("sucesso", "Loja salva com sucesso.");
        return "redirect:/admin/lojas";
    }
    
    @PostMapping("/lojas/salvar")
    public String salvarLoja(@Valid Loja loja, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println("Erro de validação: " + result.toString());
            return "admin/loja/cadastro";
        }
        lojaService.salvar(loja);
        attr.addFlashAttribute("sucesso", "Loja salva com sucesso.");
        return "redirect:/admin/lojas";
    }

    @GetMapping("/lojas/excluir/{id}")
    public String excluirLoja(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            lojaService.excluir(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro ao excluir loja: " + e.getMessage() + e.getClass());
            attr.addFlashAttribute("erro", "Não é possível excluir a loja, pois ela está vinculada a um veículo ou proposta.");
            return "redirect:/admin/lojas";
        }
        attr.addFlashAttribute("sucesso", "Loja excluída com sucesso.");
        return "redirect:/admin/lojas";
    }


    // ========== Gestão de Clientes (R1) ==========
    
    @GetMapping("/clientes")
    public String listarClientes(ModelMap model) {
        model.addAttribute("clientes", clienteService.buscarTodos());
        return "admin/cliente/lista";
    }

    @GetMapping("/clientes/cadastrar")
    public String formCadastroCliente(ModelMap model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/cliente/cadastro";
    }

    @GetMapping("/clientes/editar/{id}")
    public String formEdicaoCliente(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        return "admin/cliente/cadastro";
    }

    @PostMapping("/clientes/salvar")
    public String salvarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cliente/cadastro";
        }
        clienteService.salvar(cliente);
        attr.addFlashAttribute("sucesso", "Cliente salvo com sucesso.");
        return "redirect:/admin/clientes";
    }

    @PostMapping("/clientes/editar")
    public String editarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.getFieldErrorCount() > 2) {
            return "admin/cliente/cadastro";
        }
        clienteService.salvar(cliente);
        attr.addFlashAttribute("sucesso", "Cliente salvo com sucesso.");
        return "redirect:/admin/clientes";
    }

    @GetMapping("/clientes/excluir/{id}")
    public String excluirCliente(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            clienteService.excluir(id);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            attr.addFlashAttribute("erro", "Não é possível excluir o cliente, pois ele está vinculado a uma proposta ou veículo.");
            return "redirect:/admin/clientes";
        }
        clienteService.excluir(id);
        attr.addFlashAttribute("sucesso", "Cliente excluído com sucesso.");
        return "redirect:/admin/clientes";
    }
}