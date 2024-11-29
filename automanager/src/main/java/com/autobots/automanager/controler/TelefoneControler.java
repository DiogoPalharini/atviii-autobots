package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.servicos.TelefoneServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
    @Autowired
    private TelefoneServico servico;

    @GetMapping("/todos")
    public ResponseEntity<List<Telefone>> obterTodos() {
        List<Telefone> telefones = servico.obterTodos();
        return telefones.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(telefones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterPorId(@PathVariable Long id) {
        Optional<Telefone> telefone = servico.obterPorId(id);
        return telefone
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Telefone> cadastrar(@RequestBody Telefone telefone) {
        Telefone novoTelefone = servico.salvar(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTelefone);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Telefone> atualizar(@PathVariable Long id, @RequestBody Telefone atualizacao) {
        Optional<Telefone> telefoneOpt = servico.obterPorId(id);
        if (telefoneOpt.isPresent()) {
            Telefone telefone = telefoneOpt.get();
            telefone.setDdd(atualizacao.getDdd());
            telefone.setNumero(atualizacao.getNumero());
            telefone.setTipo(atualizacao.getTipo());
            servico.salvar(telefone);
            return ResponseEntity.ok(telefone);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (servico.obterPorId(id).isPresent()) {
            servico.excluir(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
