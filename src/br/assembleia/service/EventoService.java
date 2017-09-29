package br.assembleia.service;

import br.assembleia.entidades.Evento;
import java.util.List;

public interface EventoService {

    void salvar(Evento evento) throws IllegalArgumentException;

    List<Evento> listarTodos();

    void editar(Evento evento);

    void deletar(Evento evento);

    Evento getById(final Long id);
    
     List<Evento> eventosVisaoGral();
}
