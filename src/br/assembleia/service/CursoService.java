package br.assembleia.service;

import br.assembleia.entidades.Curso;
import java.util.List;

public interface CursoService {

    void salvar(Curso curso) throws IllegalArgumentException;

    List<Curso> listarTodos();

    void editar(Curso curso);

    void deletar(Curso curso);

    Curso getById(final Long id);
    
}
