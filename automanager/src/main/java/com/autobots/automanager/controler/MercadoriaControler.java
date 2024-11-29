package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.servicos.MercadoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {
    @Autowired
    private MercadoriaServico servico;

    @GetMapping("/todas")
    public ResponseEntity<List<Mercadoria>> obterTodas() {
        List<Mercadoria> mercadorias = servico.obterTodos();
        return mercadorias.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(mercadorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercadoria> obterPorId(@PathVariable Long id) {
        Optional<Mercadoria> mercadoria = servico.obterPorId(id);
        return mercadoria
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Mercadoria> cadastrar(@RequestBody Mercadoria mercadoria) {
        Mercadoria novaMercadoria = servico.salvar(mercadoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMercadoria);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Mercadoria> atualizar(@PathVariable Long id, @RequestBody Mercadoria atualizacao) {
        Optional<Mercadoria> mercadoriaOpt = servico.obterPorId(id);
        if (mercadoriaOpt.isPresent()) {
            Mercadoria mercadoria = mercadoriaOpt.get();
            mercadoria.setNome(atualizacao.getNome());
            mercadoria.setDescricao(atualizacao.getDescricao());
            mercadoria.setPreco(atualizacao.getPreco());
            mercadoria.setQuantidade(atualizacao.getQuantidade());
            mercadoria.setDataValidade(atualizacao.getDataValidade());
            servico.salvar(mercadoria);
            return ResponseEntity.ok(mercadoria);
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
