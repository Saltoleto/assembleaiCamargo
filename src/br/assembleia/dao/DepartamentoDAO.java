package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Departamento;
 
public interface DepartamentoDAO {
    
    
    Departamento getById(final Long id);
 
    List<Departamento> listarTodos();
 
    void salvar(Departamento departamento);
 
    void editar(Departamento departamento);
 
    void deletar(Departamento departamento);
 
    void deletarId(final Long id);
        
}