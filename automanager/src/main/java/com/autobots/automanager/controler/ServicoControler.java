package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.servicos.ServicoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoControle {
    @Autowired
    private ServicoServico servicoServico;

    @GetMapping("/todos")
    public ResponseEntity<List<Servico>> obterTodos() {
        List<Servico> servicos = servicoServico.obterTodos();
        return servicos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> obterPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoServico.obterPorId(id);
        return servico
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Servico> cadastrar(@RequestBody Servico servico) {
        servico.setDataCadastro(new Date());
        Servico novoServico = servicoServico.salvar(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico atualizacao) {
        Optional<Servico> servicoOpt = servicoServico.obterPorId(id);
        if (servicoOpt.isPresent()) {
            Servico servico = servicoOpt.get();
            servico.setNome(atualizacao.getNome());
            servico.setDescricao(atualizacao.getDescricao());
            servico.setPreco(atualizacao.getPreco());
            servico.setDataAtualizacao(new Date());
            servicoServico.salvar(servico);
            return ResponseEntity.ok(servico);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (servicoServico.obterPorId(id).isPresent()) {
            servicoServico.excluir(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
