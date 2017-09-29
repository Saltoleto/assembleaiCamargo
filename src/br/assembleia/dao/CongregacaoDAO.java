package br.assembleia.dao;
 
import br.assembleia.entidades.Congregacao;
import java.util.List;
 

 
public interface CongregacaoDAO {
    
    
    Congregacao getById(final Long id);
 
    List<Congregacao> listarTodos();
 
    void salvar(Congregacao congrecagao);
 
    void editar(Congregacao congrecagao);
 
    void deletar(Congregacao congrecagao);
 
    void deletarId(final Long id);
    
    
}