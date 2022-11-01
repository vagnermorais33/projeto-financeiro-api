package com.financeiro.projetofinanceiro3.dominio.transacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired // injetado de outra forma diferente do construtor
    private TransacaoRepository transacaoRepository;

    public List<Transacao> findAll() {
        return this.transacaoRepository.findAll();
    }

    public Page<Transacao> findAllComPaginacao(Pageable pageable) {
        return this.transacaoRepository.findAll(pageable);
    }

    public Optional<Transacao> findById(UUID id) {
        return this.transacaoRepository.findById(id);
    }

    @Transactional
    public Transacao save(Transacao transacao) {
        return this.transacaoRepository.save(transacao);
    }

    @Transactional
    public void delete(Transacao transacao) {
        this.transacaoRepository.delete(transacao);
    }

}
