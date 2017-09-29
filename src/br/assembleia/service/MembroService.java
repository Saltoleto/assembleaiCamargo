package br.assembleia.service;

import br.assembleia.entidades.Membro;
import br.assembleia.enuns.EnumSexo;
import br.assembleia.enuns.EnumSituacao;
import java.util.List;

public interface MembroService {

    void salvar(Membro membro) throws IllegalArgumentException;

    List<Membro> listarTodos();

    void editar(Membro membro);

    void deletar(Membro membro);

    Integer buscarQtdTotalMembros();

    Integer buscarQtdMembrosSituacao(EnumSituacao situacao);

    Integer buscarQtdMembrosDizimistas();

    List<Membro> aniversariantesMes();

    List<Membro> aniversariantesRelatorio(Long mes);

    List<Membro> listarPorSexoCargo(EnumSexo sexo);
    
    List<Membro> listarObreiros(EnumSexo sexo);

}
