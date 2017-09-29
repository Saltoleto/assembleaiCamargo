package br.assembleia.dao.impl;

import br.assembleia.dao.ProfessorDAO;
import br.assembleia.entidades.Professor;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ProfessorDAOImpl implements ProfessorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Professor getById(Long id) {
        return entityManager.find(Professor.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Professor> listarTodos() {
        return entityManager.createQuery("FROM " + Professor.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Professor professor) {
        entityManager.merge(professor);
        entityManager.flush();
    }

    @Override
    public void editar(Professor professor) {
        entityManager.merge(professor);
        entityManager.flush();
    }

    @Override
    public void deletar(Professor professor) {
        professor = getById(professor.getId());
        entityManager.remove(professor);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Professor professor = getById(id);
        entityManager.remove(professor);
        entityManager.flush();
    }


}
