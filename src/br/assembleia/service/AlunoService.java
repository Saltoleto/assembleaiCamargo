package br.assembleia.service;

import br.assembleia.entidades.Aluno;
import java.util.List;

public interface AlunoService {

    void salvar(Aluno aluno) throws IllegalArgumentException;

    List<Aluno> listarTodos();

    void editar(Aluno aluno);

    void deletar(Aluno aluno);

    Aluno getById(final Long id);

    
}
