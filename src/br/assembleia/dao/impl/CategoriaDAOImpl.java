package br.assembleia.dao.impl;

import br.assembleia.dao.CategoriaDAO;
import br.assembleia.entidades.Categoria;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Categoria getById(Long id) {
        return entityManager.find(Categoria.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Categoria> listarTodos() {
        return entityManager.createQuery("FROM " + Categoria.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Categoria categoria) {
        entityManager.merge(categoria);
        entityManager.flush();
    }

    @Override
    public void editar(Categoria categoria) {
        entityManager.merge(categoria);
        entityManager.flush();
    }

    @Override
    public void deletar(Categoria categoria) {
        categoria = getById(categoria.getId());
        entityManager.remove(categoria);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Categoria categoria = getById(id);
        entityManager.remove(categoria);
        entityManager.flush();
    }

}
