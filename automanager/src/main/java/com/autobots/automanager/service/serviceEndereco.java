package com.autobots.automanager.servicos;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.repositorios.RepositorioEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServico {
    @Autowired
    private RepositorioEndereco repositorio;

    public List<Endereco> obterTodos() {
        return repositorio.findAll();
    }

    public Optional<Endereco> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return repositorio.save(endereco);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
