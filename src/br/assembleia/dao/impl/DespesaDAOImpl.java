package br.assembleia.dao.impl;

import br.assembleia.dao.DespesaDAO;
import br.assembleia.entidades.Despesa;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class DespesaDAOImpl implements DespesaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Despesa getById(Long id) {
        return entityManager.find(Despesa.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Despesa> listarTodos() {
        return entityManager.createQuery("FROM " + Despesa.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Despesa despesa) {
        entityManager.merge(despesa);
        entityManager.flush();
    }

    @Override
    public void editar(Despesa despesa) {
        entityManager.merge(despesa);
        entityManager.flush();
    }

    @Override
    public void deletar(Despesa despesa) {
        despesa = getById(despesa.getId());
        entityManager.remove(despesa);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Despesa despesa = getById(id);
        entityManager.remove(despesa);
        entityManager.flush();
    }

    @Override
    public BigDecimal valorDespesaPeriodo(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select SUM(r.valor) from Despesa r ");
        sql.append("Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public List<Despesa> listarDespesasMesAno(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select r from Despesa r ");
        sql.append(" Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        List<Despesa> result = q.getResultList();

        return result;
    }

    @Override
    public BigDecimal listarDespesasPagas() {
        StringBuilder sql = new StringBuilder();
        sql.append("Select sum(d.valor) from Despesa d ");
        sql.append("where d.pago = true ");

        Query q = entityManager.createQuery(sql.toString());
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public List<Despesa> despesasPagarVisaoGeral(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select r from Despesa r ");
        sql.append(" Where extract(MONTH FROM r.data) = ? "
                + "and extract(YEAR FROM r.data) = ? AND r.pago = false ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        List<Despesa> result = q.getResultList();

        return result;
    }

    @Override
    public BigDecimal buscarDespesaGrafico(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select SUM(r.valor) from Despesa r ");
        sql.append("Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);        
        q.setParameter(2, ano);        
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public BigDecimal listarDespesasCategoriaMesAno(Integer mes, Integer ano, Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select sum(d.valor) from Despesa d ");
        sql.append("Where d.categoria.id = ? and extract(MONTH FROM d.data) = ? and extract(YEAR FROM d.data) = ? ");
        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, id);
        q.setParameter(2, mes);
        q.setParameter(3, ano);

        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

}
