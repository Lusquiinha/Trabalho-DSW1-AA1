package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Usuario;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.IPropostaService;
import dsw.concessionaria.service.spec.IVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dsw.concessionaria.security.MyUserDetails;

@Controller
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private IPropostaService propostaService;

    @Autowired
    private IVeiculoService veiculoService;

    // R5: Exibe o formulário para o cliente fazer uma proposta
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/fazer/{id}")
    public String formProposta(@PathVariable("id") Long id, ModelMap model) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        Proposta proposta = new Proposta();
        proposta.setVeiculo(veiculo);
        
        model.addAttribute("proposta", proposta);
        return "proposta/formulario"; // -> /templates/proposta/formulario.html
    }

    // R5: Salva a proposta enviada pelo cliente
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/salvar")
    public String salvarProposta(@Valid Proposta proposta, BindingResult result, @AuthenticationPrincipal MyUserDetails userDetails, RedirectAttributes attr) {
        
        if (result.hasErrors()) {
            return "proposta/formulario";
        }

        try {
            // Associa o cliente logado à proposta
            proposta.setCliente((Cliente) userDetails.getUsuario());
            propostaService.salvar(proposta);
            attr.addFlashAttribute("sucesso", "Proposta enviada com sucesso!");
        } catch (RuntimeException e) {
            attr.addFlashAttribute("erro", e.getMessage());
        }

        // Redireciona para a home após a proposta
        return "redirect:/";
    }
}