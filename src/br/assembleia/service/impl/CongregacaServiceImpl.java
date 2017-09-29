package br.assembleia.service.impl;

import br.assembleia.dao.CongregacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Congregacao;

import br.assembleia.service.CongregacaoService;
import java.util.List;

@Service
@Transactional
public class CongregacaServiceImpl implements CongregacaoService {

    @Autowired
    private CongregacaoDAO dao;

    @Override
    public void salvar(Congregacao congregacao) throws IllegalArgumentException {
        dao.salvar(congregacao);
    }

    @Override
    public List<Congregacao> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Congregacao congregacao) {
        dao.editar(congregacao);
    }

    @Override
    public void deletar(Congregacao congregacao) {
        dao.deletar(congregacao);
    }

    @Override
    public Congregacao getById(Long id) {
        return dao.getById(id);
    }

}
