package br.assembleia.dao.impl;

import br.assembleia.dao.UsuarioDAO;
import br.assembleia.entidades.Usuario;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario getById(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> listarTodos() {
        return entityManager.createQuery("FROM " + Usuario.class.getName())
                .getResultList();
    }

    @Override
    public void salvar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void editar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        usuario = getById(usuario.getId());
        entityManager.remove(usuario);
    }

    @Override
    public void deletarId(final Long id) {
        Usuario usuario = getById(id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario findByLogin(String login, String password) {
        Usuario usuario = null;
        try {
            usuario = (Usuario) entityManager.createQuery(""
                    + "FROM Usuario AS u "
                    + "WHERE u.login='" + login + "' "
                    + "AND u.senha=md5('" + password + "')").getSingleResult();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return usuario;
    }

}
