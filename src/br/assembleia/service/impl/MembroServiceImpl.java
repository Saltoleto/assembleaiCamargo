package br.assembleia.service.impl;

import br.assembleia.dao.MembroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Membro;
import br.assembleia.enuns.EnumSexo;
import br.assembleia.enuns.EnumSituacao;
import br.assembleia.service.MembroService;
import java.util.List;

@Service
@Transactional
public class MembroServiceImpl implements MembroService {

    @Autowired
    private MembroDAO dao;

    @Override
    public void salvar(Membro membro) throws IllegalArgumentException {
        dao.salvar(membro);
    }

    @Override
    public List<Membro> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Membro membro) {
        dao.editar(membro);
    }

    @Override
    public void deletar(Membro membro) {
        dao.deletar(membro);
    }

    @Override
    public Integer buscarQtdTotalMembros() {
        return dao.buscarQtdTotalMembros();
    }

    @Override
    public Integer buscarQtdMembrosSituacao(EnumSituacao situacao) {
        return dao.buscarQtdMembrosSituacao(situacao);
    }

    @Override
    public List<Membro> aniversariantesMes() {
       return dao.aniversariantesMes();
    }

    @Override
    public Integer buscarQtdMembrosDizimistas() {
        return dao.buscarQtdMembrosDizimistas();
    }

    @Override
    public List<Membro> aniversariantesRelatorio(Long mes) {
        return dao.aniversariantesRelatorio(mes);
    }

    @Override
    public List<Membro> listarPorSexoCargo(EnumSexo sexo) {
        return dao.listarPorSexoCargo(sexo);
    }

    @Override
    public List<Membro> listarObreiros(EnumSexo sexo) {
        return dao.listarObreiros(sexo);
    }

  

 

}
