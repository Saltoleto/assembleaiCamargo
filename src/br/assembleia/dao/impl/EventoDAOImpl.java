package br.assembleia.dao.impl;

import br.assembleia.dao.EventoDAO;
import br.assembleia.entidades.Evento;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class EventoDAOImpl implements EventoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Evento getById(Long id) {
        return entityManager.find(Evento.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Evento> listarTodos() {
        return entityManager.createQuery("FROM " + Evento.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Evento evento) {
        entityManager.merge(evento);
        entityManager.flush();
    }

    @Override
    public void editar(Evento evento) {
        entityManager.merge(evento);
        entityManager.flush();
    }

    @Override
    public void deletar(Evento evento) {
        evento = getById(evento.getId());
        entityManager.remove(evento);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Evento evento = getById(id);
        entityManager.remove(evento);
        entityManager.flush();
    }

    @Override
    public List<Evento> eventosVisaoGral() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM Evento e ");
        sql.append("Where extract(MONTH FROM e.dataInicio) = extract(MONTH FROM now()) ");

        Query q = entityManager.createQuery(sql.toString());     

        List<Evento> result = q.getResultList();

        return result;
    }

}
