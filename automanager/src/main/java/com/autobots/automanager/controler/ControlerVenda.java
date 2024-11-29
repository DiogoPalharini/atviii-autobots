package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.servicos.VendaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venda")
public class VendaControle {
    @Autowired
    private VendaServico vendaServico;

    @GetMapping("/todas")
    public ResponseEntity<List<Venda>> obterTodas() {
        List<Venda> vendas = vendaServico.obterTodos();
        return vendas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> obterPorId(@PathVariable Long id) {
        Optional<Venda> venda = vendaServico.obterPorId(id);
        return venda
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Venda> cadastrar(@RequestBody Venda venda) {
        venda.setDataVenda(new Date());
        Venda novaVenda = vendaServico.salvar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda atualizacao) {
        Optional<Venda> vendaOpt = vendaServico.obterPorId(id);
        if (vendaOpt.isPresent()) {
            Venda venda = vendaOpt.get();
            venda.setValorTotal(atualizacao.getValorTotal());
            venda.setItensVendidos(atualizacao.getItensVendidos());
            vendaServico.salvar(venda);
            return ResponseEntity.ok(venda);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (vendaServico.obterPorId(id).isPresent()) {
            vendaServico.excluir(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
