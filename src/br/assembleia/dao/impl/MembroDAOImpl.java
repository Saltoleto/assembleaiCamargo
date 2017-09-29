package br.assembleia.dao.impl;

import br.assembleia.dao.MembroDAO;
import br.assembleia.entidades.Membro;
import br.assembleia.enuns.EnumSexo;
import br.assembleia.enuns.EnumSituacao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MembroDAOImpl implements MembroDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Membro getById(Long id) {
        return entityManager.find(Membro.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Membro> listarTodos() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m FROM Membro m ");
        sql.append("Order By m.nome ");

        Query q = entityManager.createQuery(sql.toString());

        List<Membro> result = q.getResultList();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Membro> listarPorSexoCargo(EnumSexo sexo) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m FROM Membro m ");
        sql.append("join m.cargo c ");
        sql.append("Where m.sexo = ? AND c.descricao = 'Membro' ");
        sql.append("Order By m.nome ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, sexo);

        List<Membro> result = q.getResultList();
        return result;
    }

    @Override
    public void salvar(Membro membro) {
        entityManager.merge(membro);
        entityManager.flush();
    }

    @Override
    public void editar(Membro membro) {
        entityManager.merge(membro);
        entityManager.flush();
    }

    @Override
    public void deletar(Membro membro) {
        membro = getById(membro.getId());
        entityManager.remove(membro);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Membro membro = getById(id);
        entityManager.remove(membro);
        entityManager.flush();
    }

    @Override
    public Integer buscarQtdTotalMembros() {
        StringBuilder sql = new StringBuilder();
        sql.append("Select Count(*) from Membro ");

        Query q = entityManager.createQuery(sql.toString());

        Long result = (Long) q.getSingleResult();
        Integer n = Integer.valueOf(result.toString());

        return n;
    }

    @Override
    public Integer buscarQtdMembrosSituacao(EnumSituacao situacao) {
        StringBuilder sql = new StringBuilder();
        sql.append("Select Count(*) from Membro ");
        sql.append("Where situacao = ? ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, situacao);

        Long result = (Long) q.getSingleResult();
        Integer n = Integer.valueOf(result.toString());

        return n;
    }

    @Override
    public List<Membro> aniversariantesMes() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m FROM Membro m ");
        sql.append("Where extract(MONTH FROM m.dataNascimento) = extract(MONTH FROM now()) ");

        Query q = entityManager.createQuery(sql.toString());

        List<Membro> result = q.getResultList();

        return result;
    }

    @Override
    public Integer buscarQtdMembrosDizimistas() {
        StringBuilder sql = new StringBuilder();
        sql.append("Select Count(*) from Membro m  ");
        sql.append("Where m.dizimista = true ");

        Query q = entityManager.createQuery(sql.toString());

        Long result = (Long) q.getSingleResult();
        Integer n = Integer.valueOf(result.toString());

        return n;
    }

    @Override
    public List<Membro> aniversariantesRelatorio(Long mes) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m FROM Membro m ");
        sql.append("Where extract(MONTH FROM m.dataNascimento) = ? order by extract(DAY FROM m.dataNascimento) ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, mes.intValue());

        List<Membro> result = q.getResultList();

        return result;
    }

    @Override
    public List<Membro> listarObreiros(EnumSexo sexo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT m FROM Membro m ");
        sql.append("join m.cargo c ");
        sql.append("Where m.sexo = ? AND c.descricao != 'Membro' ");
        sql.append("Order By m.nome ");

        Query q = entityManager.createQuery(sql.toString());
        q.setParameter(1, sexo);

        List<Membro> result = q.getResultList();
        return result;
    }

}
