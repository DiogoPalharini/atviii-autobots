package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {

    @Autowired
    private RepositorioEmpresa repositorio;

    // Obter todas as empresas
    @GetMapping("/todas")
    public ResponseEntity<List<Empresa>> obterEmpresas() {
        List<Empresa> empresas = repositorio.findAll();
        if (empresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(empresas);
    }

    // Obter empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = repositorio.findById(id);
        if (empresa.isPresent()) {
            return ResponseEntity.ok(empresa.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Cadastrar nova empresa
    @PostMapping("/cadastrar")
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody Empresa empresa) {
        if (empresa.getId() != null && repositorio.existsById(empresa.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Empresa novaEmpresa = repositorio.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }

    // Atualizar empresa existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa atualizacao) {
        Optional<Empresa> empresaExistente = repositorio.findById(id);
        if (empresaExistente.isPresent()) {
            Empresa empresa = empresaExistente.get();
            // Atualize apenas os campos relevantes
            empresa.setRazaoSocial(atualizacao.getRazaoSocial() != null ? atualizacao.getRazaoSocial() : empresa.getRazaoSocial());
            empresa.setNomeFantasia(atualizacao.getNomeFantasia() != null ? atualizacao.getNomeFantasia() : empresa.getNomeFantasia());
            empresa.setTelefones(atualizacao.getTelefones() != null ? atualizacao.getTelefones() : empresa.getTelefones());
            empresa.setEndereco(atualizacao.getEndereco() != null ? atualizacao.getEndereco() : empresa.getEndereco());
            empresa.setUsuarios(atualizacao.getUsuarios() != null ? atualizacao.getUsuarios() : empresa.getUsuarios());
            empresa.setMercadorias(atualizacao.getMercadorias() != null ? atualizacao.getMercadorias() : empresa.getMercadorias());
            empresa.setServicos(atualizacao.getServicos() != null ? atualizacao.getServicos() : empresa.getServicos());
            empresa.setVendas(atualizacao.getVendas() != null ? atualizacao.getVendas() : empresa.getVendas());

            repositorio.save(empresa);
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Excluir empresa por ID
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirEmpresa(@PathVariable Long id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
