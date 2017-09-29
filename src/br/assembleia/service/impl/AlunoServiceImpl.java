package br.assembleia.service.impl;

import br.assembleia.dao.AlunoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Aluno;
import br.assembleia.service.AlunoService;
import java.util.List;

@Service
@Transactional
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoDAO dao;

    @Override
    public void salvar(Aluno aluno) throws IllegalArgumentException {
        dao.salvar(aluno);
    }

    @Override
    public List<Aluno> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Aluno aluno) {
        dao.editar(aluno);
    }

    @Override
    public void deletar(Aluno aluno) {
        dao.deletar(aluno);
    }

    @Override
    public Aluno getById(Long id) {
        return dao.getById(id);
    }


}
