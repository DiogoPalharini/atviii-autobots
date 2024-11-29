package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.RepositorioTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneServico {
    @Autowired
    private RepositorioTelefone repositorio;

    public List<Telefone> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Telefone> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Telefone salvar(Telefone telefone) {
        return repositorio.save(telefone);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
