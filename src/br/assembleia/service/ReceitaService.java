package br.assembleia.service;

import br.assembleia.entidades.Receita;
import java.math.BigDecimal;
import java.util.List;

public interface ReceitaService {

    void salvar(Receita receita) throws IllegalArgumentException;

    List<Receita> listarTodos();

    void editar(Receita receita);

    void deletar(Receita receita);

    Receita getById(final Long id);

    BigDecimal valorReceitaPeriodo(Integer mes, Integer ano);
    
     List<Receita> listarReceitasMesAno(Integer mes, Integer ano);
     
     List<Receita> listarUltimasReceitasVisao(Integer mes, Integer ano);
     
     BigDecimal listarReceitasRecebidas();
     
     List<Receita> buscarReceitaMembroData(Long mes);
     
     BigDecimal buscarReceitaGrafico(Long mes, Integer ano);
     
     BigDecimal listarReceitasCategoriaMesAno(Integer mes, Integer ano, Long id);
}
