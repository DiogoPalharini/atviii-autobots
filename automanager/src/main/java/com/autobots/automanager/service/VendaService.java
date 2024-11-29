package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.RepositorioVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaServico {
    @Autowired
    private RepositorioVenda repositorio;

    public List<Venda> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Venda> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Venda salvar(Venda venda) {
        return repositorio.save(venda);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
