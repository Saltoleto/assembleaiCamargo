package br.assembleia.dao;

import java.util.List;

import br.assembleia.entidades.Despesa;
import java.math.BigDecimal;

public interface DespesaDAO {

    Despesa getById(final Long id);

    List<Despesa> listarTodos();

    void salvar(Despesa despesa);

    void editar(Despesa despesa);

    void deletar(Despesa despesa);

    void deletarId(final Long id);

    BigDecimal valorDespesaPeriodo(Integer mes, Integer ano);

    List<Despesa> listarDespesasMesAno(Integer mes, Integer ano);

    List<Despesa> despesasPagarVisaoGeral(Integer mes, Integer ano);

    BigDecimal listarDespesasPagas();

    BigDecimal buscarDespesaGrafico(Integer mes, Integer ano);
    
    BigDecimal listarDespesasCategoriaMesAno(Integer mes, Integer ano, Long id);

}
