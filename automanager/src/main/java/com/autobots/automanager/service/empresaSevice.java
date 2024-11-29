package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class EmpresaServico {
    @Autowired
    private RepositorioEmpresa repositorio;

    public List<Empresa> obterTodas() {
        return repositorio.findAll();
    }

    public Optional<Empresa> obterPorId(Long id) {
        return repositorio.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return repositorio.save(empresa);
    }

    public void excluir(Long id) {
        repositorio.deleteById(id);
    }
}
