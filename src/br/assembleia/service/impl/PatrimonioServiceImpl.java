package br.assembleia.service.impl;

import br.assembleia.dao.PatrimonioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Patrimonio;
import br.assembleia.service.PatrimonioService;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PatrimonioServiceImpl implements PatrimonioService {

    @Autowired
    private PatrimonioDAO dao;

    @Override
    public void salvar(Patrimonio patrimonio) throws IllegalArgumentException {
        dao.salvar(patrimonio);
    }

    @Override
    public List<Patrimonio> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Patrimonio patrimonio) {
        dao.editar(patrimonio);
    }

    @Override
    public void deletar(Patrimonio patrimonio) {
        dao.deletar(patrimonio);
    }

    @Override
    public Patrimonio getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public BigDecimal valorPatrimonio() {
       return dao.valorPatrimonio();
    }
    

}
