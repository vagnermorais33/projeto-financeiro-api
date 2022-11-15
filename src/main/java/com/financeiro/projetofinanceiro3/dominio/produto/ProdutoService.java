package com.financeiro.projetofinanceiro3.dominio.produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired // injetado de outra forma diferente do construtor
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    public Page<Produto> findAllComPaginacao(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    }

    public Optional<Produto> findById(UUID id) {
        return this.produtoRepository.findById(id);
    }

    @Transactional
    public Produto save(Produto produto) {
        return this.produtoRepository.save(produto);
    }

    @Transactional
    public void delete(Produto produto) {
        this.produtoRepository.delete(produto);
    }

}