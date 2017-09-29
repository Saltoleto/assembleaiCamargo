package br.assembleia.dao;

import java.util.List;

import br.assembleia.entidades.Curso;

public interface CursoDAO {

    Curso getById(final Long id);

    List<Curso> listarTodos();

    void salvar(Curso curso);

    void editar(Curso curso);

    void deletar(Curso curso);

    void deletarId(final Long id);

}
