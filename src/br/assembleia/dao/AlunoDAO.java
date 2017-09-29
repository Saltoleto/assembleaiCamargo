package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Aluno;
 
public interface AlunoDAO {
    
    
    Aluno getById(final Long id);
 
    List<Aluno> listarTodos();
 
    void salvar(Aluno acervo);
 
    void editar(Aluno acervo);
 
    void deletar(Aluno acervo);
 
    void deletarId(final Long id);
        
}