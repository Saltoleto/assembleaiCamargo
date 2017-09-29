package br.assembleia.service.impl;

import br.assembleia.dao.DespesaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Despesa;
import br.assembleia.service.DespesaService;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DespesaServiceImpl implements DespesaService {

    @Autowired
    private DespesaDAO dao;

    @Override
    public void salvar(Despesa despesa) throws IllegalArgumentException {
        dao.salvar(despesa);
    }

    @Override
    public List<Despesa> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Despesa despesa) {
        dao.editar(despesa);
    }

    @Override
    public void deletar(Despesa despesa) {
        dao.deletar(despesa);
    }

    @Override
    public Despesa getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public BigDecimal valorDespesaPeriodo(Integer mes, Integer ano) {
       return dao.valorDespesaPeriodo(mes, ano);
    }

    @Override
    public List<Despesa> listarDespesasMesAno(Integer mes, Integer ano) {
      return dao.listarDespesasMesAno(mes, ano);
    }

    @Override
    public BigDecimal listarDespesasPagas() {
        return dao.listarDespesasPagas();
    }

    @Override
    public List<Despesa> despesasPagarVisaoGeral(Integer mes, Integer ano) {
        return dao.despesasPagarVisaoGeral(mes, ano);
    }

    @Override
    public BigDecimal buscarDespesaGrafico(Integer mes, Integer ano) {
        return  dao.buscarDespesaGrafico(mes,ano);
    }

    @Override
    public BigDecimal listarDespesasCategoriaMesAno(Integer mes, Integer ano, Long id) {
        return dao.listarDespesasCategoriaMesAno(mes, ano, id);
    }
    

}
