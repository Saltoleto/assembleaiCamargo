package br.assembleia.service.impl;


import br.assembleia.dao.EmprestimoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Emprestimo;
import br.assembleia.service.EmprestimoService;
import java.util.List;

@Service
@Transactional
public class EmprestimoServiceImpl implements EmprestimoService {

    @Autowired
    private EmprestimoDAO dao;

    @Override
    public void salvar(Emprestimo emprestimo) throws IllegalArgumentException {
        dao.salvar(emprestimo);
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Emprestimo emprestimo) {
        dao.editar(emprestimo);
    }

    @Override
    public void deletar(Emprestimo emprestimo) {
        dao.deletar(emprestimo);
    }

    @Override
    public Emprestimo getById(Long id) {
        return dao.getById(id);
    }

}
