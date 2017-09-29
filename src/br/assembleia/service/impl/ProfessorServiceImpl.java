package br.assembleia.service.impl;

import br.assembleia.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Professor;
import br.assembleia.service.ProfessorService;
import java.util.List;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDAO dao;

    @Override
    public void salvar(Professor professor) throws IllegalArgumentException {
        dao.salvar(professor);
    }

    @Override
    public List<Professor> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Professor professor) {
        dao.editar(professor);
    }

    @Override
    public void deletar(Professor professor) {
        dao.deletar(professor);
    }

    @Override
    public Professor getById(Long id) {
        return dao.getById(id);
    }

}
