package br.assembleia.service.impl;

import br.assembleia.dao.DepartamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Departamento;
import br.assembleia.service.DepartamentoService;
import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoDAO dao;

    @Override
    public void salvar(Departamento departamento) throws IllegalArgumentException {
        dao.salvar(departamento);
    }

    @Override
    public List<Departamento> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Departamento departamento) {
        dao.editar(departamento);
    }

    @Override
    public void deletar(Departamento departamento) {
        dao.deletar(departamento);
    }

    @Override
    public Departamento getById(Long id) {
        return dao.getById(id);
    }

}
