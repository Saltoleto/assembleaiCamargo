package br.assembleia.service.impl;

import br.assembleia.dao.AcervoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Acervo;
import br.assembleia.enuns.EnumStatusAcervo;
import br.assembleia.service.AcervoService;
import java.util.List;

@Service
@Transactional
public class AcervoServiceImpl implements AcervoService {

    @Autowired
    private AcervoDAO dao;

    @Override
    public void salvar(Acervo acervo) throws IllegalArgumentException {
        dao.salvar(acervo);
    }

    @Override
    public List<Acervo> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Acervo acervo) {
        dao.editar(acervo);
    }

    @Override
    public void deletar(Acervo acervo) {
        dao.deletar(acervo);
    }

    @Override
    public Acervo getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Acervo> listaAcervoStatus(EnumStatusAcervo statusAcervo) {
        return dao.listaAcervoStatus(statusAcervo);
    }

}
