package br.assembleia.service.impl;

import br.assembleia.dao.CursoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Curso;
import br.assembleia.service.CursoService;
import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDAO dao;

    @Override
    public void salvar(Curso curso) throws IllegalArgumentException {
        dao.salvar(curso);
    }

    @Override
    public List<Curso> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Curso curso) {
        dao.editar(curso);
    }

    @Override
    public void deletar(Curso curso) {
        dao.deletar(curso);
    }

    @Override
    public Curso getById(Long id) {
        return dao.getById(id);
    }

}
