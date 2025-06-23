package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.security.MyUserDetails;
import dsw.concessionaria.service.spec.IVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/veiculos")
// A anotação foi corrigida para usar 'STORE', sem o prefixo 'ROLE_'.
@PreAuthorize("hasRole('STORE')")
public class VeiculoController {

    @Autowired
    private IVeiculoService veiculoService;

    @GetMapping("/listar")
    public String listar(ModelMap model, @AuthenticationPrincipal MyUserDetails userDetails) {
        
        Loja lojaLogada = (Loja) userDetails.getUsuario();
        
        // ===================================================================
        // ALTERAÇÃO ADICIONADA AQUI
        // Enviamos o objeto da loja para a view, para que possamos usar
        // dados como o nome da loja na página.
        model.addAttribute("loja", lojaLogada);
        // ===================================================================
        
        // R6: Lista todos os veículos da loja logada
        model.addAttribute("veiculos", veiculoService.buscarPorLoja(lojaLogada));
        return "veiculo/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model) {
        model.addAttribute("veiculo", new Veiculo());
        return "veiculo/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Veiculo veiculo, BindingResult result, @AuthenticationPrincipal MyUserDetails userDetails, RedirectAttributes attr) {
    
        // Os logs de depuração foram removidos para deixar o código mais limpo.
        if (result.hasErrors()) {
            return "veiculo/cadastro";
        }
        
        Loja lojaLogada = (Loja) userDetails.getUsuario();
        veiculo.setLoja(lojaLogada);

        veiculoService.salvar(veiculo);
        attr.addFlashAttribute("sucesso", "Veículo cadastrado com sucesso.");
        return "redirect:/veiculos/listar";
    }
}