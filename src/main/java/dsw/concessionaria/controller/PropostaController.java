package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Cliente;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Proposta;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.security.MyUserDetails;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private IPropostaService propostaService;

    @Autowired
    private IVeiculoService veiculoService;

    // R5: Exibe o formulário para o cliente fazer uma proposta
    @PreAuthorize("hasRole('CLIENT')") // CORRIGIDO
    @GetMapping("/fazer/{id}")
    public String formProposta(@PathVariable("id") Long id, ModelMap model) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        Proposta proposta = new Proposta();
        proposta.setVeiculo(veiculo);
        
        model.addAttribute("proposta", proposta);
        return "proposta/formulario";
    }

    // R5: Salva a proposta enviada pelo cliente
    @PreAuthorize("hasRole('CLIENT')") // CORRIGIDO
    @PostMapping("/salvar")
    public String salvarProposta(@Valid Proposta proposta, BindingResult result, @AuthenticationPrincipal MyUserDetails userDetails, RedirectAttributes attr) {
        
        if (result.hasErrors()) {
            return "proposta/formulario";
        }

        try {
            proposta.setCliente((Cliente) userDetails.getUsuario());
            propostaService.salvar(proposta);
            attr.addFlashAttribute("sucesso", "Proposta enviada com sucesso!");
        } catch (RuntimeException e) {
            attr.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/propostas/cliente";
    }
    
    // R7: Exibe a lista de propostas feitas pelo cliente logado.
    @PreAuthorize("hasRole('CLIENT')") // CORRIGIDO
    @GetMapping("/cliente")
    public String listarPropostasCliente(ModelMap model, @AuthenticationPrincipal MyUserDetails userDetails) {
        Cliente cliente = (Cliente) userDetails.getUsuario();
        model.addAttribute("propostas", propostaService.buscarTodosPorCliente(cliente));
        return "proposta/listaCliente";
    }
    
    // R8: Exibe a lista de propostas recebidas pela loja logada.
    @PreAuthorize("hasRole('STORE')") // CORRIGIDO
    @GetMapping("/loja")
    public String listarPropostasLoja(ModelMap model, @AuthenticationPrincipal MyUserDetails userDetails) {
        Loja loja = (Loja) userDetails.getUsuario();
        model.addAttribute("propostas", propostaService.buscarTodosPorLoja(loja));
        return "proposta/listaLoja";
    }

    // Exibe o formulário de avaliação para a loja
    @PreAuthorize("hasRole('STORE')") // CORRIGIDO
    @GetMapping("/avaliar/{id}")
    public String formAvaliacao(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("proposta", propostaService.buscarPorId(id));
        return "proposta/avaliacao";
    }

    // Processa a avaliação enviada pela loja
    @PreAuthorize("hasRole('STORE')") // CORRIGIDO
    @PostMapping("/avaliar")
    public String avaliar(
            @RequestParam("propostaId") Long propostaId,
            @RequestParam("status") String status,
            @RequestParam(value = "contraProposta", required = false) String contraProposta,
            @RequestParam(value = "linkReuniao", required = false) String linkReuniao,
            RedirectAttributes attr) {
        
        propostaService.atualizarStatus(propostaId, status, contraProposta, linkReuniao);
        attr.addFlashAttribute("sucesso", "Proposta avaliada com sucesso!");
        return "redirect:/propostas/loja";
    }
}