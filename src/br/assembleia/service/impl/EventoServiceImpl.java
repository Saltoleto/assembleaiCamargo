package br.assembleia.service.impl;

import br.assembleia.dao.EventoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Evento;
import br.assembleia.service.EventoService;
import java.util.List;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoDAO dao;

    @Override
    public void salvar(Evento evento) throws IllegalArgumentException {
        dao.salvar(evento);
    }

    @Override
    public List<Evento> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Evento evento) {
        dao.editar(evento);
    }

    @Override
    public void deletar(Evento evento) {
        dao.deletar(evento);
    }

    @Override
    public Evento getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Evento> eventosVisaoGral() {
        return dao.eventosVisaoGral();
    }

}
