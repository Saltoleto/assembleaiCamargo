package br.assembleia.service;

import br.assembleia.entidades.Congregacao;

import java.util.List;

public interface CongregacaoService {

    void salvar(Congregacao congregacao) throws IllegalArgumentException;

    List<Congregacao> listarTodos();

    void editar(Congregacao congregacao);

    void deletar(Congregacao congregacao);

    Congregacao getById(final Long id);

}
