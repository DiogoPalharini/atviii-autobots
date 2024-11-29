package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoriaServico {
    @Autowired
    private RepositorioMercadoria repositorio;

    public List<Mercadoria> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Mercadoria> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Mercadoria salvar(Mercadoria mercadoria) {
        return repositorio.save(mercadoria);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
