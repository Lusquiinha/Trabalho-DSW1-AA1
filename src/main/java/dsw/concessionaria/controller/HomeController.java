package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.IVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IVeiculoService veiculoService;

    @GetMapping
    public String home(@RequestParam(name = "modelo", required = false) String modelo, ModelMap model) {
        // R4: Lista todos os veículos ou filtra por modelo
        List<Veiculo> veiculos = (modelo != null && !modelo.isEmpty()) ? 
                                 veiculoService.buscarPorModelo(modelo) : 
                                 veiculoService.buscarTodos();
        
        model.addAttribute("veiculos", veiculos);
        return "home"; // -> /src/main/resources/templates/home.html
    }

    @GetMapping("/veiculo/{id}")
    public String detalheVeiculo(@PathVariable("id") Long id, ModelMap model) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        if (veiculo == null) {
            // Se o veículo não for encontrado, redireciona para a home
            return "redirect:/";
        }
        model.addAttribute("veiculo", veiculo);
        return "detalhe_veiculo"; // -> /templates/detalhe_veiculo.html
    }
}