package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.ILojaService;
import dsw.concessionaria.service.spec.IVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/veiculos")
@PreAuthorize("hasRole('ROLE_LOJA')") // Apenas lojas podem acessar
public class VeiculoController {

    @Autowired
    private IVeiculoService veiculoService;
    
    @Autowired
    private ILojaService lojaService;

    @GetMapping("/listar")
    public String listar(ModelMap model, @AuthenticationPrincipal Usuario usuario) {
        // Busca a loja logada a partir do usuário
        Loja loja = lojaService.buscarPorCnpj(usuario.getUsername()); // Supondo que o username da loja é o CNPJ
        
        // R6: Lista todos os veículos da loja logada
        model.addAttribute("veiculos", veiculoService.buscarPorLoja(loja));
        return "veiculo/lista"; // -> /src/main/resources/templates/veiculo/lista.html
    }

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("veiculo", new Veiculo());
        return "veiculo/cadastro"; // -> .../templates/veiculo/cadastro.html
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Veiculo veiculo, BindingResult result, @AuthenticationPrincipal Usuario usuario, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "veiculo/cadastro";
        }
        
        // Busca a loja logada e a associa ao novo veículo
        Loja loja = lojaService.buscarPorCnpj(usuario.getUsername());
        veiculo.setLoja(loja);

        veiculoService.salvar(veiculo);
        attr.addFlashAttribute("sucesso", "Veículo cadastrado com sucesso.");
        return "redirect:/veiculos/listar";
    }
}