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

import java.util.List;

@Service
@Transactional(readOnly = false)
public class VeiculoServiceImpl implements IVeiculoService {

    @Autowired
    private VeiculoDAO veiculoDAO;

    @Autowired
    private ImagemDAO imagemDAO;

    @Override
    public Long salvar(Veiculo veiculo) {
        return veiculoDAO.save(veiculo).getId();
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
        return veiculoDAO.findAllByVendidoFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> buscarPorModelo(String modelo) {
        return veiculoDAO.findAllByVendidoFalseAndModeloContainingIgnoreCase(modelo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Veiculo> buscarPorLoja(Loja loja) {
        return veiculoDAO.findAllByLoja(loja);
    }

    @Override   
    public Long salvarImagem(Imagem imagem) {
        return imagemDAO.save(imagem).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Imagem buscarImagemPorId(Long id) {
        return imagemDAO.findById(id).orElse(null);
    }

}