package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Emprestimo;
 
public interface EmprestimoDAO {
    
    
    Emprestimo getById(final Long id);
 
    List<Emprestimo> listarTodos();
 
    void salvar(Emprestimo emprestimo);
 
    void editar(Emprestimo emprestimo);
 
    void deletar(Emprestimo emprestimo);
 
    void deletarId(final Long id);
}