package dsw.concessionaria.service.spec;

import dsw.concessionaria.domain.Imagem;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import java.util.List;

public interface IVeiculoService {

    void salvar(Veiculo veiculo);

    void excluir(Long id);

    Veiculo buscarPorId(Long id);

    List<Veiculo> buscarTodos();
    
    // Requisito R4
    List<Veiculo> buscarPorModelo(String modelo);
    
    // Requisito R6
    List<Veiculo> buscarPorLoja(Loja loja);

    Imagem buscarImagemPorId(Long id);

    void salvarImagem(Imagem imagem);
}