package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.servicos.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
    @Autowired
    private EnderecoServico servico;

    @GetMapping("/todos")
    public ResponseEntity<List<Endereco>> obterTodos() {
        List<Endereco> enderecos = servico.obterTodos();
        return enderecos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = servico.obterPorId(id);
        return endereco
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco) {
        Endereco novoEndereco = servico.salvar(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco atualizacao) {
        Optional<Endereco> enderecoOpt = servico.obterPorId(id);
        if (enderecoOpt.isPresent()) {
            Endereco endereco = enderecoOpt.get();
            endereco.setEstado(atualizacao.getEstado());
            endereco.setCidade(atualizacao.getCidade());
            endereco.setBairro(atualizacao.getBairro());
            endereco.setRua(atualizacao.getRua());
            endereco.setNumero(atualizacao.getNumero());
            endereco.setComplemento(atualizacao.getComplemento());
            endereco.setCep(atualizacao.getCep());
            servico.salvar(endereco);
            return ResponseEntity.ok(endereco);
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
