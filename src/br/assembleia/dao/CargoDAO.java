package br.assembleia.dao;
 
import java.util.List;
 
import br.assembleia.entidades.Cargo;
 
public interface CargoDAO {
    Cargo getById(final Long id);
 
    List<Cargo> listarTodos();
 
    void salvar(Cargo cargo);
 
    void editar(Cargo cargo);
 
    void deletar(Cargo cargo);
 
    void deletarId(final Long id);
}