package br.assembleia.service;

import br.assembleia.entidades.Despesa;
import java.math.BigDecimal;
import java.util.List;

public interface DespesaService {

    void salvar(Despesa despesa) throws IllegalArgumentException;

    List<Despesa> listarTodos();

    void editar(Despesa despesa);

    void deletar(Despesa despesa);

    Despesa getById(final Long id);

    BigDecimal valorDespesaPeriodo(Integer mes, Integer ano);
    
     List<Despesa> listarDespesasMesAno(Integer mes, Integer ano);
     
     List<Despesa> despesasPagarVisaoGeral(Integer mes, Integer ano);
     
     BigDecimal listarDespesasPagas();
     
     BigDecimal buscarDespesaGrafico(Integer mes, Integer ano);
     
     BigDecimal listarDespesasCategoriaMesAno(Integer mes, Integer ano, Long id);
}
