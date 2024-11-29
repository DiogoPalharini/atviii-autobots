package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.servicos.VeiculoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {
    @Autowired
    private VeiculoServico veiculoServico;

    @GetMapping("/todos")
    public ResponseEntity<List<Veiculo>> obterTodos() {
        List<Veiculo> veiculos = veiculoServico.obterTodos();
        return veiculos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoServico.obterPorId(id);
        return veiculo
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoServico.salvar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo atualizacao) {
        Optional<Veiculo> veiculoOpt = veiculoServico.obterPorId(id);
        if (veiculoOpt.isPresent()) {
            Veiculo veiculo = veiculoOpt.get();
            veiculo.setMarca(atualizacao.getMarca());
            veiculo.setModelo(atualizacao.getModelo());
            veiculo.setPlaca(atualizacao.getPlaca());
            veiculo.setCor(atualizacao.getCor());
            veiculo.setAnoFabricacao(atualizacao.getAnoFabricacao());
            veiculo.setAnoModelo(atualizacao.getAnoModelo());
            veiculoServico.salvar(veiculo);
            return ResponseEntity.ok(veiculo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (veiculoServico.obterPorId(id).isPresent()) {
            veiculoServico.excluir(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
