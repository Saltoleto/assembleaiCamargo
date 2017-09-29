package br.assembleia.dao;

import java.util.List;

import br.assembleia.entidades.Usuario;

public interface UsuarioDAO {

    Usuario getById(final Long id);

    List<Usuario> listarTodos();

    void salvar(Usuario usuario);

    void editar(Usuario usuario);

    void deletar(Usuario usuario);

    void deletarId(final Long id);

    Usuario findByLogin(String login, String password);
}
