package br.assembleia.dao;
 
import br.assembleia.entidades.Professor;
import java.util.List;
 
 
public interface ProfessorDAO {
    
    
     Professor getById(final Long id);
 
    List<Professor> listarTodos();
 
    void salvar(Professor professor);
 
    void editar(Professor professor);
 
    void deletar(Professor professor);
 
    void deletarId(final Long id);
    
    
}