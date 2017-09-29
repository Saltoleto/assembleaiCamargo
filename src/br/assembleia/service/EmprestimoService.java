package br.assembleia.service;

import br.assembleia.entidades.Emprestimo;
import java.util.List;

public interface EmprestimoService {

    void salvar(Emprestimo emprestimo) throws IllegalArgumentException;

    List<Emprestimo> listarTodos();

    void editar(Emprestimo emprestimo);

    void deletar(Emprestimo emprestimo);

    Emprestimo getById(final Long id);
}
