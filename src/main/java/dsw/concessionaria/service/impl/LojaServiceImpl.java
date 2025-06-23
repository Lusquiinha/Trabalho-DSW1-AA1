package dsw.concessionaria.service.impl;

import dsw.concessionaria.dao.LojaDAO;
import dsw.concessionaria.domain.Loja;
import dsw.concessionaria.service.spec.ILojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class LojaServiceImpl implements ILojaService {

    @Autowired
    private LojaDAO lojaDAO;

    @Override
    public void salvar(Loja loja) {
        lojaDAO.save(loja);
    }

    @Override
    public void excluir(Long id) {
        lojaDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Loja buscarPorId(Long id) {
        return lojaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loja> buscarTodos() {
        return lojaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Loja buscarPorCnpj(String cnpj) {
        return lojaDAO.findByCnpj(cnpj);
    }
}