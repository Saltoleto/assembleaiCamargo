package br.assembleia.dao.impl;

import br.assembleia.dao.EmprestimoDAO;
import br.assembleia.entidades.Emprestimo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EmprestimoDAOImpl implements EmprestimoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Emprestimo getById(Long id) {
           return entityManager.find(Emprestimo.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Emprestimo> listarTodos() {
        return entityManager.createQuery("FROM " + Emprestimo.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Emprestimo emprestimo) {
        entityManager.merge(emprestimo);
        entityManager.flush();
    }

    @Override
    public void editar(Emprestimo emprestimo) {
        entityManager.merge(emprestimo);
        entityManager.flush();
    }

    @Override
    public void deletar(Emprestimo emprestimo) {
        emprestimo = getById(emprestimo.getId());
        entityManager.remove(emprestimo);
        entityManager.flush();        
    }

    @Override
    public void deletarId(final Long id) {
        Emprestimo emprestimo = getById(id);
        entityManager.remove(emprestimo);
        entityManager.flush();
    }

}
