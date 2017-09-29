package br.assembleia.dao.impl;

import br.assembleia.dao.ReceitaDAO;
import br.assembleia.entidades.Receita;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ReceitaDAOImpl implements ReceitaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Receita getById(Long id) {
        return entityManager.find(Receita.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Receita> listarTodos() {
        return entityManager.createQuery("FROM " + Receita.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Receita receita) {
        entityManager.merge(receita);
        entityManager.flush();
    }

    @Override
    public void editar(Receita receita) {
        entityManager.merge(receita);
        entityManager.flush();
    }

    @Override
    public void deletar(Receita receita) {
        receita = getById(receita.getId());
        entityManager.remove(receita);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Receita receita = getById(id);
        entityManager.remove(receita);
        entityManager.flush();
    }

    @Override
    public BigDecimal valorReceitaPeriodo(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select SUM(r.valor) from Receita r ");
        sql.append("Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public List<Receita> listarReceitasMesAno(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select r from Receita r ");
        sql.append(" Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        List<Receita> result = q.getResultList();

        return result;
    }

    @Override
    public BigDecimal listarReceitasRecebidas() {
        StringBuilder sql = new StringBuilder();
        sql.append("Select sum(r.valor) from Receita r ");
        sql.append("where r.recebido = true ");

        Query q = entityManager.createQuery(sql.toString());
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public List<Receita> listarUltimasReceitasVisao(Integer mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select r from Receita r ");
        sql.append(" Where extract(MONTH FROM r.data) = ? "
                + "and extract(YEAR FROM r.data) = ? AND r.recebido = true ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes);
        q.setParameter(2, ano);
        List<Receita> result = q.getResultList();

        return result;
    }

    @Override
    public List<Receita> buscarReceitaMembroData(Long mes) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select r FROM Receita r ");
        sql.append(" inner join r.membro m ");
        sql.append(" Where extract(MONTH FROM r.data) = ? AND r.recebido = true "
                + "order by m.nome ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes.intValue());
        List<Receita> result = q.getResultList();

        return result;
    }

    @Override
    public BigDecimal buscarReceitaGrafico(Long mes, Integer ano) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select sum(r.valor) from Receita r ");
        sql.append("Where extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");
        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes.intValue());
        q.setParameter(2, ano);
        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

    @Override
    public BigDecimal listarReceitasCategoriaMesAno(Integer mes, Integer ano, Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select sum(r.valor) from Receita r ");
        sql.append("Where r.categoria.id = ? and extract(MONTH FROM r.data) = ? and extract(YEAR FROM r.data) = ? ");
        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, id);
        q.setParameter(2, mes);
        q.setParameter(3, ano);

        BigDecimal result = (BigDecimal) q.getSingleResult();

        return result;
    }

}
