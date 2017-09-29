package br.assembleia.dao.impl;

import br.assembleia.dao.CargoDAO;
import br.assembleia.entidades.Cargo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CargoDAOImpl implements CargoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cargo getById(Long id) {
        return entityManager.find(Cargo.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cargo> listarTodos() {
        return entityManager.createQuery("FROM " + Cargo.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Cargo cargo) {
        entityManager.merge(cargo);
        entityManager.flush();
    }

    @Override
    public void editar(Cargo cargo) {
        entityManager.merge(cargo);
        entityManager.flush();
    }

    @Override
    public void deletar(Cargo cargo) {
        cargo = getById(cargo.getId());
        entityManager.remove(cargo);
        entityManager.flush();
    }

    @Override
    public void deletarId(final Long id) {
        Cargo cargo = getById(id);
        entityManager.remove(cargo);
        entityManager.flush();
    }

}
