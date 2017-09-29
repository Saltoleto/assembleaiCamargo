package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Evento;
 
public interface EventoDAO {
    
    
    Evento getById(final Long id);
 
    List<Evento> listarTodos();
    
    List<Evento> eventosVisaoGral();
 
    void salvar(Evento evento);
 
    void editar(Evento evento);
 
    void deletar(Evento evento);
 
    void deletarId(final Long id);
        
}