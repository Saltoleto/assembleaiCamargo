package br.assembleia.service.impl;

import br.assembleia.dao.CargoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.assembleia.entidades.Cargo;
import br.assembleia.service.CargoService;
import java.util.List;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDAO dao;

    @Override
    public void salvar(Cargo cargo) throws IllegalArgumentException {        
        dao.salvar(cargo);
    }

    @Override
    public List<Cargo> listarTodos() {
        return dao.listarTodos();
    }

    @Override
    public void editar(Cargo cargo) {
        dao.editar(cargo);
    }

    @Override
    public void deletar(Cargo cargo) {
        dao.deletar(cargo);
    }

}
