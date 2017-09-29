package br.assembleia.dao.impl;

import br.assembleia.dao.PatrimonioDAO;
import br.assembleia.entidades.Patrimonio;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PatrimonioDAOImpl implements PatrimonioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patrimonio getById(Long id) {
        return entityManager.find(Patrimonio.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Patrimonio> listarTodos() {
        return entityManager.createQuery("FROM " + Patrimonio.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Patrimonio patrimonio) {
        entityManager.merge(patrimonio);
        entityManager.flush();
    }

    @Override
    public void editar(Patrimonio patrimonio) {
        entityManager.merge(patrimonio);
        entityManager.flush();
    }

    @Override
    public void deletar(Patrimonio patrimonio) {
        patrimonio = getById(patrimonio.getId());
        entityManager.remove(patrimonio);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Patrimonio patrimonio = getById(id);
        entityManager.remove(patrimonio);
        entityManager.flush();
    }

    @Override
    public BigDecimal valorPatrimonio() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT SUM(valorTotal) as valor FROM Patrimonio");

        Query q = entityManager.createQuery(sql.toString());
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

}
