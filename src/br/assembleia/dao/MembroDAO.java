package br.assembleia.dao;

import java.util.List;

import br.assembleia.entidades.Membro;
import br.assembleia.enuns.EnumSexo;
import br.assembleia.enuns.EnumSituacao;

public interface MembroDAO {

    Membro getById(final Long id);

    List<Membro> listarTodos();

    void salvar(Membro membro);

    void editar(Membro membro);

    void deletar(Membro membro);

    void deletarId(final Long id);

    Integer buscarQtdTotalMembros();

    Integer buscarQtdMembrosSituacao(EnumSituacao situacao);

    Integer buscarQtdMembrosDizimistas();

    List<Membro> aniversariantesMes();

    List<Membro> aniversariantesRelatorio(Long mes);

    List<Membro> listarPorSexoCargo(EnumSexo sexo);
    
    List<Membro> listarObreiros(EnumSexo sexo);
        

}
