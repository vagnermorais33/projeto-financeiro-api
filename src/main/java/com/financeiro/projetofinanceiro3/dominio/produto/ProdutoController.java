package com.financeiro.projetofinanceiro3.dominio.produto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {

    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> selectAll() {

        List<Produto> listaDeProdutos = this.produtoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(listaDeProdutos);

    }

    @GetMapping("selectAllComPaginacao")
    public ResponseEntity<Page<Produto>> selectAllComPaginacao(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAllComPaginacao(pageable));
    }

    /* GET IDBIYID */
    @GetMapping("/{id}")
    public ResponseEntity<Object> selectById(@PathVariable("id") UUID id) {

        Optional<Produto> produtoEncotrado = this.produtoService.findById(id);

        if (!produtoEncotrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Produto não foi encotrada");

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(produtoEncotrado.get());

        }

    }

    @PostMapping()
    public ResponseEntity<Object> insert(@RequestBody @Valid ProdutoDto produtoDaRequisicao) {

        Produto produto = new Produto();

        BeanUtils.copyProperties(produtoDaRequisicao, produto);

        produto.setAtivo(true);
        produto.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        produto.setUpdateaAt(LocalDateTime.now(ZoneId.of("UTC")));

        Produto produtoSalva = this.produtoService.save(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalva);
    }

    // List<Produto> todosProdutos = produtoService.findAll();

    // if (todosProdutos.size() > 0) {

    // Produto ultimoProduto = todosProdutos.get(todosProdutos.size() - 1);

    // if (transacao.getTipo().equals("ENTRADA")) {
    // Double saldoFinal = ultimaTransacao.getSaldo() + transacao.getValor();
    // transacao.setSaldo(saldoFinal);

    // } else if (transacao.getTipo().equals("SAIDA")) {
    // Double saldoFinal = ultimaTransacao.getSaldo() - transacao.getValor();
    // transacao.setSaldo(saldoFinal);
    // }

    // } else {

    // if (transacao.getTipo().equals("ENTRADA")) {
    // Double saldoFinal = transacao.getValor();
    // transacao.setSaldo(saldoFinal);

    // } else if (transacao.getTipo().equals("SAIDA")) {
    // Double saldoFinal = -transacao.getValor();
    // transacao.setSaldo(saldoFinal);
    // }

    // }

    // Produto produtoSalva = this.produtoService.save(produto);

    // return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalva);

    @GetMapping("consultaUltimoSaldo")
    public ResponseEntity<Double> consultaUltimoSaldo() {

        return ResponseEntity.status(HttpStatus.OK).body(20.0);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID idDaRequisicao,
            @RequestBody ProdutoDto produtoDaRequisicao) {

        Optional<Produto> produtoOptional = this.produtoService.findById(idDaRequisicao);

        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Produto não foi encotrado");

        }

        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDaRequisicao, produto);

        produto.setId(produtoOptional.get().getId());
        produto.setCreatedAt(produtoOptional.get().getCreatedAt());
        produto.setUpdateaAt(LocalDateTime.now(ZoneId.of("UTC")));

        Produto produtoAtualizada = this.produtoService.save(produto);

        return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID idDaRequisicao) {

        Optional<Produto> produtoOptional = this.produtoService.findById(idDaRequisicao);

        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }

        produtoService.delete(produtoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");

    }

}
