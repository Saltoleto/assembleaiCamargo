package br.assembleia.service;

import br.assembleia.entidades.Categoria;
import java.util.List;

public interface CategoriaService {

    void salvar(Categoria categoria) throws IllegalArgumentException;

    List<Categoria> listarTodos();

    void editar(Categoria categoria);

    void deletar(Categoria categoria);
}
