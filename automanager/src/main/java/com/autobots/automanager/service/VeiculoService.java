package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServico {
    @Autowired
    private RepositorioVeiculo repositorio;

    public List<Veiculo> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Veiculo> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repositorio.save(veiculo);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
