package br.assembleia.service;

import br.assembleia.entidades.Usuario;
import java.util.List;

public interface UsuarioService {

    void salvar(Usuario usuario) throws IllegalArgumentException;

    List<Usuario> listarTodos();

    void editar(Usuario usuario);

    void deletar(Usuario usuario);

    Usuario findByLogin(String login, String password);
    
}
