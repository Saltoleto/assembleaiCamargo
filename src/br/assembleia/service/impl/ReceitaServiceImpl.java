package br.assembleia.service.impl;

import br.assembleia.dao.ReceitaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Receita;
import br.assembleia.service.ReceitaService;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private ReceitaDAO dao;

    @Override
    public void salvar(Receita receita) throws IllegalArgumentException {
        dao.salvar(receita);
    }

    @Override
    public List<Receita> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Receita receita) {
        dao.editar(receita);
    }

    @Override
    public void deletar(Receita receita) {
        dao.deletar(receita);
    }

    @Override
    public Receita getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public BigDecimal valorReceitaPeriodo(Integer mes, Integer ano) {
       return dao.valorReceitaPeriodo(mes, ano);
    }

    @Override
    public List<Receita> listarReceitasMesAno(Integer mes, Integer ano) {
      return dao.listarReceitasMesAno(mes, ano);
    }

    @Override
    public BigDecimal listarReceitasRecebidas() {
        return dao.listarReceitasRecebidas();
    }

    @Override
    public List<Receita> listarUltimasReceitasVisao(Integer mes, Integer ano) {
       return dao.listarUltimasReceitasVisao(mes, ano);
    }

    @Override
    public List<Receita> buscarReceitaMembroData(Long mes) {
        return dao.buscarReceitaMembroData(mes);
    }

    @Override
    public BigDecimal buscarReceitaGrafico(Long mes , Integer ano) {
       return dao.buscarReceitaGrafico(mes , ano);
    }

    @Override
    public BigDecimal listarReceitasCategoriaMesAno(Integer mes, Integer ano, Long id) {
        return dao.listarReceitasCategoriaMesAno(mes, ano, id);
    }
    

}
