package br.assembleia.dao.impl;

import br.assembleia.dao.AcervoDAO;
import br.assembleia.entidades.Acervo;
import br.assembleia.enuns.EnumStatusAcervo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class AcervoDAOImpl implements AcervoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Acervo getById(Long id) {
        return entityManager.find(Acervo.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Acervo> listarTodos() {
        return entityManager.createQuery("FROM " + Acervo.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Acervo acervo) {
        entityManager.merge(acervo);
        entityManager.flush();
    }

    @Override
    public void editar(Acervo acervo) {
        entityManager.merge(acervo);
        entityManager.flush();
    }

    @Override
    public void deletar(Acervo acervo) {
        acervo = getById(acervo.getId());
        entityManager.remove(acervo);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Acervo acervo = getById(id);
        entityManager.remove(acervo);
        entityManager.flush();
    }

    @Override
    public List<Acervo> listaAcervoStatus(EnumStatusAcervo statusAcervo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM Acervo a ");
        sql.append("WHERE a.status = ?");

     
        Query q = entityManager.createQuery(sql.toString());

        q.setParameter(1, statusAcervo);

        List<Acervo> result = q.getResultList();

        return result;

    } 

}
