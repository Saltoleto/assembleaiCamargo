package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Categoria;
 
public interface CategoriaDAO {
    Categoria getById(final Long id);
 
    List<Categoria> listarTodos();
 
    void salvar(Categoria categoria);
 
    void editar(Categoria categoria);
 
    void deletar(Categoria categoria);
 
    void deletarId(final Long id);
}