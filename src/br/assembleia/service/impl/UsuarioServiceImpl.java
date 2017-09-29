package br.assembleia.service.impl;

import br.assembleia.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Usuario;
import br.assembleia.service.UsuarioService;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO dao;

    @Override
    public void salvar(Usuario usuario) throws IllegalArgumentException {
        dao.salvar(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Usuario usuario) {
        dao.editar(usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        dao.deletar(usuario);
    }

    @Override
    public Usuario findByLogin(String login, String password) {
        return dao.findByLogin(login, password);
    }

}
