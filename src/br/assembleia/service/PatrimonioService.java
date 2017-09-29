package br.assembleia.service;

import br.assembleia.entidades.Patrimonio;
import java.math.BigDecimal;
import java.util.List;

public interface PatrimonioService {

    void salvar(Patrimonio patrimonio) throws IllegalArgumentException;

    List<Patrimonio> listarTodos();

    void editar(Patrimonio patrimonio);

    void deletar(Patrimonio patrimonio);

    Patrimonio getById(final Long id);

    BigDecimal valorPatrimonio();
}
