package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.VeiculoDAO;
import dsw.concessionaria.dao.ImagemDAO;
import dsw.concessionaria.domain.Imagem;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.domain.Veiculo;
import dsw.concessionaria.service.spec.IVeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class VeiculoServiceImpl implements IVeiculoService {

    @Autowired
    private VeiculoDAO veiculoDAO;

    @Autowired
    private ImagemDAO imagemDAO;

    @Override
    public void salvar(Veiculo veiculo) {
        veiculoDAO.save(veiculo);
    }

    @Override
    public void excluir(Long id) {
        veiculoDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Veiculo buscarPorId(Long id) {
        return veiculoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> buscarTodos() {
        List<Veiculo> listaVeiculos = new ArrayList<>();
        veiculoDAO.findAll().forEach(listaVeiculos::add);
        return listaVeiculos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> buscarPorModelo(String modelo) {
        return veiculoDAO.findAllByModeloContainingIgnoreCase(modelo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> buscarPorLoja(Loja loja) {
        return veiculoDAO.findAllByLoja(loja);
    }

    @Override   
    public void salvarImagem(Imagem imagem) {
        imagemDAO.save(imagem);
    }

    @Override
    @Transactional(readOnly = true)
    public Imagem buscarImagemPorId(Long id) {
        return imagemDAO.findById(id).orElse(null);
    }
}