package br.assembleia.dao;

import java.util.List;

import br.assembleia.entidades.Receita;
import java.math.BigDecimal;

public interface ReceitaDAO {

    Receita getById(final Long id);

    List<Receita> listarTodos();

    void salvar(Receita receita);

    void editar(Receita receita);

    void deletar(Receita receita);

    void deletarId(final Long id);

    BigDecimal valorReceitaPeriodo(Integer mes, Integer ano);

    List<Receita> listarReceitasMesAno(Integer mes, Integer ano);

    List<Receita> listarUltimasReceitasVisao(Integer mes, Integer ano);

    BigDecimal listarReceitasRecebidas();

    List<Receita> buscarReceitaMembroData(Long mes);
    
    BigDecimal buscarReceitaGrafico(Long mes, Integer ano);
    
    BigDecimal listarReceitasCategoriaMesAno(Integer mes, Integer ano, Long id);

}
