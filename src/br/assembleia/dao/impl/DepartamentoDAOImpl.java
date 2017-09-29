package br.assembleia.dao.impl;

import br.assembleia.dao.DepartamentoDAO;
import br.assembleia.entidades.Departamento;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoDAOImpl implements DepartamentoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Departamento getById(Long id) {
        return entityManager.find(Departamento.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Departamento> listarTodos() {
        return entityManager.createQuery("FROM " + Departamento.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Departamento departamento) {
        entityManager.merge(departamento);
        entityManager.flush();
    }

    @Override
    public void editar(Departamento departamento) {
        entityManager.merge(departamento);
        entityManager.flush();
    }

    @Override
    public void deletar(Departamento departamento) {
        departamento = getById(departamento.getId());
        entityManager.remove(departamento);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Departamento departamento = getById(id);
        entityManager.remove(departamento);
        entityManager.flush();
    }


}
