package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.security.MyUserDetails; // IMPORTAR
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
@PreAuthorize("hasRole('ROLE_STORE')")
public class VeiculoController {

    @Autowired
    private IVeiculoService veiculoService;


    @GetMapping("/listar")
    public String listar(ModelMap model, @AuthenticationPrincipal MyUserDetails userDetails) {
        
        // Pegamos o objeto Loja diretamente do usuário autenticado.
        // Isso é mais seguro e eficiente, pois não precisa de outra consulta ao banco.
        Loja lojaLogada = (Loja) userDetails.getUsuario();
        
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
    
    // Este bloco irá verificar e imprimir os erros no console
    if (result.hasErrors()) {
        System.out.println("=============================================");
        System.out.println("### ERROS DE VALIDAÇÃO ENCONTRADOS ###");
        // Itera sobre todos os erros e imprime os detalhes
        result.getAllErrors().forEach(error -> {
            System.out.println(error.toString());
        });
        System.out.println("=============================================");
        return "veiculo/cadastro";
    }
    
    // O resto do código só executa se não houver erros
    Loja lojaLogada = (Loja) userDetails.getUsuario();
    veiculo.setLoja(lojaLogada);

    veiculoService.salvar(veiculo);
    attr.addFlashAttribute("sucesso", "Veículo cadastrado com sucesso.");
    return "redirect:/veiculos/listar";
}
}