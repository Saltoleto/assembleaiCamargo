package br.assembleia.dao.impl;

import br.assembleia.dao.AlunoDAO;
import br.assembleia.entidades.Aluno;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AlunoDAOImpl implements AlunoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Aluno getById(Long id) {
        return entityManager.find(Aluno.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Aluno> listarTodos() {
        return entityManager.createQuery("FROM " + Aluno.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Aluno aluno) {
        entityManager.merge(aluno);
        entityManager.flush();
    }

    @Override
    public void editar(Aluno aluno) {
        entityManager.merge(aluno);
        entityManager.flush();
    }

    @Override
    public void deletar(Aluno aluno) {
        aluno = getById(aluno.getId());
        entityManager.remove(aluno);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Aluno aluno = getById(id);
        entityManager.remove(aluno);
        entityManager.flush();
    }

}
