package br.assembleia.service;

import br.assembleia.entidades.Departamento;
import java.util.List;

public interface DepartamentoService {

    void salvar(Departamento departamento) throws IllegalArgumentException;

    List<Departamento> listarTodos();

    void editar(Departamento departamento);

    void deletar(Departamento departamento);

    Departamento getById(final Long id);
}
