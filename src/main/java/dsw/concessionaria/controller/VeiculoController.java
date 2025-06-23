package dsw.concessionaria.controller;

import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.domain.Imagem; // IMPORTAR
import dsw.concessionaria.security.MyUserDetails; // IMPORTAR
import dsw.concessionaria.service.spec.IVeiculoService;
import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; 
// import org.springframework.web.multipart.MultipartFile; //seria utilizado para imagens, mas não está implementado



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
    public String salvar(@Valid Veiculo veiculo, BindingResult result, 
                     @AuthenticationPrincipal MyUserDetails userDetails, 
                     RedirectAttributes attr
                     ,@RequestParam("imagens") MultipartFile[] imagens
                     ) {

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
    if(imagens.length > 10){
        return "veiculo/cadastro";
    }
    // Salvar as imagens associadas ao veículo
    System.out.println(imagens.length + " imagens recebidas para o veículo: " + veiculo.getModelo());
    for (MultipartFile imagem : imagens) {
        if (!imagem.isEmpty()) {
            try {
                Imagem novaImagem = new Imagem();
                novaImagem.setNomeArquivo(imagem.getOriginalFilename());
                novaImagem.setDados(imagem.getBytes()); // Salva os bytes da imagem
                novaImagem.setVeiculo(veiculo);
                veiculoService.salvarImagem(novaImagem);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar imagem: " + e.getMessage());
            }
        }
    }

    attr.addFlashAttribute("sucesso", "Veículo cadastrado com sucesso.");
    return "redirect:/veiculos/listar";
}

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> exibirImagem(@PathVariable Long id) {
        Imagem imagem = veiculoService.buscarImagemPorId(id);
        if (imagem != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(imagem.getDados());
        }
        return ResponseEntity.notFound().build();
    }

        /**
     * Exibe o formulário de edição preenchido com os dados de um veículo existente.
     * @param id O ID do veículo a ser editado (vindo da URL).
     * @param model O objeto Model para enviar o veículo para a view.
     * @return O caminho para a página de cadastro, que será reutilizada para edição.
     */
    @GetMapping("/editar/{id}")
    public String formEditarVeiculo(@PathVariable("id") Long id, ModelMap model) {
        // Busca o veículo pelo ID no banco de dados.
        Veiculo veiculo = veiculoService.buscarPorId(id);
        // Adiciona o veículo encontrado ao modelo.
        model.addAttribute("veiculo", veiculo);
        // Reutiliza a mesma página de cadastro para a edição.
        return "veiculo/cadastro";
    }
    

    @PostMapping("/editar")
    public String editar(@Valid Veiculo veiculo, BindingResult result, 
                     @AuthenticationPrincipal MyUserDetails userDetails, 
                     RedirectAttributes attr
                    ,@RequestParam("imagens") MultipartFile[] imagens
                     ) {

    // Este bloco irá verificar e imprimir os erros no console
    if (result.getFieldErrorCount() > 2) {
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

    // Salvar as imagens associadas ao veículo
    if(imagens.length > 10){
        return "veiculo/cadastro";
    }
    System.out.println(imagens.length + " imagens recebidas para o veículo: " + veiculo.getModelo());
    for (MultipartFile imagem : imagens) {
        if (!imagem.isEmpty()) {
            try {
                Imagem novaImagem = new Imagem();
                novaImagem.setNomeArquivo(imagem.getOriginalFilename());
                novaImagem.setDados(imagem.getBytes()); // Salva os bytes da imagem
                novaImagem.setVeiculo(veiculo);
                veiculoService.salvarImagem(novaImagem);

            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar imagem: " + e.getMessage());
            }
        }
    }

    attr.addFlashAttribute("sucesso", "Veículo atualizado com sucesso.");
    return "redirect:/veiculos/listar";
}

}