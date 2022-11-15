package com.financeiro.projetofinanceiro3.dominio.usuarioProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProdutoService {

    @Autowired // injetado de outra forma diferente do construtor
    private UsuarioProdutoRepository usuarioProdutoRepository;

    public List<UsuarioProduto> findAll() {
        return this.usuarioProdutoRepository.findAll();
    }

    public Optional<UsuarioProduto> findById(UUID id) {
        return this.usuarioProdutoRepository.findById(id);
    }

    @Transactional
    public UsuarioProduto save(UsuarioProduto usuarioProduto) {
        return this.usuarioProdutoRepository.save(usuarioProduto);
    }

    @Transactional
    public void delete(UsuarioProduto usuarioProduto) {
        this.usuarioProdutoRepository.delete(usuarioProduto);
    }

}
