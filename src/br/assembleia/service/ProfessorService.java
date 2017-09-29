package br.assembleia.service;

import br.assembleia.entidades.Professor;
import java.util.List;

public interface ProfessorService {

    void salvar(Professor professor) throws IllegalArgumentException;

    List<Professor> listarTodos();

    void editar(Professor professor);

    void deletar(Professor professor);

    Professor getById(final Long id);
    
}
