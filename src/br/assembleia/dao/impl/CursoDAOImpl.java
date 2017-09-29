package br.assembleia.dao.impl;

import br.assembleia.dao.CursoDAO;
import br.assembleia.entidades.Curso;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CursoDAOImpl implements CursoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Curso getById(Long id) {
        return entityManager.find(Curso.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> listarTodos() {
        return entityManager.createQuery("FROM " + Curso.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Curso curso) {
        entityManager.merge(curso);
        entityManager.flush();
    }

    @Override
    public void editar(Curso curso) {
        entityManager.merge(curso);
        entityManager.flush();
    }

    @Override
    public void deletar(Curso curso) {
        curso = getById(curso.getId());
        entityManager.remove(curso);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Curso curso = getById(id);
        entityManager.remove(curso);
        entityManager.flush();
    }


}
