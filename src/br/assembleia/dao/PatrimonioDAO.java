package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Patrimonio;
import java.math.BigDecimal;
 
public interface PatrimonioDAO {
    
    
    Patrimonio getById(final Long id);
 
    List<Patrimonio> listarTodos();
 
    void salvar(Patrimonio patrimonio);
 
    void editar(Patrimonio patrimonio);
 
    void deletar(Patrimonio patrimonio);
 
    void deletarId(final Long id);
    
    BigDecimal valorPatrimonio();
       
}