package br.assembleia.service;

import br.assembleia.entidades.Acervo;
import br.assembleia.enuns.EnumStatusAcervo;
import java.util.List;

public interface AcervoService {

    void salvar(Acervo acervo) throws IllegalArgumentException;

    List<Acervo> listarTodos();

    void editar(Acervo acervo);

    void deletar(Acervo acervo);

    Acervo getById(final Long id);

    List<Acervo> listaAcervoStatus(EnumStatusAcervo statusAcervo);
}
