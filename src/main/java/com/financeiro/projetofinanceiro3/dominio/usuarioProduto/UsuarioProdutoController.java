package com.financeiro.projetofinanceiro3.dominio.usuarioProduto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
@RequestMapping("/usuarioProduto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioProdutoController {

    final UsuarioProdutoService usuarioProdutoService;

    public UsuarioProdutoController(UsuarioProdutoService usuarioProdutoService) {
        this.usuarioProdutoService = usuarioProdutoService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioProduto>> selectAll() {

        List<UsuarioProduto> listaDeProdutos = this.usuarioProdutoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(listaDeProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> selectById(@PathVariable("id") UUID id) {

        Optional<UsuarioProduto> usuarioProdutoEncontrado = this.usuarioProdutoService.findById(id);

        if (!usuarioProdutoEncontrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioProdutoEncontrado.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> insert(@RequestBody @Valid UsuarioProdutoDto usuarioProdutoDaRequisicao) {

        UsuarioProduto usuarioProduto = new UsuarioProduto();

        BeanUtils.copyProperties(usuarioProdutoDaRequisicao, usuarioProduto);

        usuarioProduto.setAtivo(true);
        usuarioProduto.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        usuarioProduto.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        UsuarioProduto usuarioProdutoSalva = this.usuarioProdutoService.save(usuarioProduto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioProdutoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID idDaRequisicao,
            @RequestBody UsuarioProdutoDto usuarioProdutoDaRequisicao) {

        Optional<UsuarioProduto> usuarioProdutoOptional = this.usuarioProdutoService.findById(idDaRequisicao);

        if (!usuarioProdutoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado!");

        }

        UsuarioProduto usuarioProduto = new UsuarioProduto();
        BeanUtils.copyProperties(usuarioProdutoDaRequisicao, usuarioProduto);

        usuarioProduto.setId(usuarioProdutoOptional.get().getId());
        usuarioProduto.setCreatedAt(usuarioProdutoOptional.get().getCreatedAt());
        usuarioProduto.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        UsuarioProduto usuarioProdutoAtualizado = this.usuarioProdutoService.save(usuarioProduto);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioProdutoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID idDaRequisicao) {

        Optional<UsuarioProduto> usuarioProdutoOptional = this.usuarioProdutoService.findById(idDaRequisicao);

        if (!usuarioProdutoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        usuarioProdutoService.delete(usuarioProdutoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");

    }
}
