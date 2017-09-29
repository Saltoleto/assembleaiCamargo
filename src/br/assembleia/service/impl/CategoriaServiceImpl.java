package br.assembleia.service.impl;

import br.assembleia.dao.CategoriaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Categoria;
import br.assembleia.service.CategoriaService;
import java.util.List;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDAO dao;

    @Override
    public void salvar(Categoria categoria) throws IllegalArgumentException {        
        dao.salvar(categoria);
    }

    @Override
    public List<Categoria> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Categoria categoria) {
        dao.editar(categoria);
    }

    @Override
    public void deletar(Categoria categoria) {
        dao.deletar(categoria);
    }

}
