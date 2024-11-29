package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.RepositorioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoServico {
    @Autowired
    private RepositorioServico repositorio;

    public List<Servico> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Servico> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Servico salvar(Servico servico) {
        return repositorio.save(servico);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
