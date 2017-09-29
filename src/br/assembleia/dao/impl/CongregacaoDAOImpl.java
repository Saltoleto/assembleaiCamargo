package br.assembleia.dao.impl;

import br.assembleia.dao.CongregacaoDAO;
import br.assembleia.entidades.Congregacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CongregacaoDAOImpl implements CongregacaoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Congregacao getById(Long id) {
        return entityManager.find(Congregacao.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Congregacao> listarTodos() {
        return entityManager.createQuery("FROM " + Congregacao.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Congregacao congregacao) {
        entityManager.merge(congregacao);
        entityManager.flush();
    }

    @Override
    public void editar(Congregacao congregacao) {
        entityManager.merge(congregacao);
        entityManager.flush();
    }

    @Override
    public void deletar(Congregacao congregacao) {
        congregacao = getById(congregacao.getId());
        entityManager.remove(congregacao);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Congregacao congregacao = getById(id);
        entityManager.remove(congregacao);
        entityManager.flush();
    }


}
