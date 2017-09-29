package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Acervo;
import br.assembleia.enuns.EnumStatusAcervo;
 
public interface AcervoDAO {
    
    
    Acervo getById(final Long id);
 
    List<Acervo> listarTodos();
 
    void salvar(Acervo acervo);
 
    void editar(Acervo acervo);
 
    void deletar(Acervo acervo);
 
    void deletarId(final Long id);
    
    List<Acervo> listaAcervoStatus(EnumStatusAcervo statusAcervo);
}