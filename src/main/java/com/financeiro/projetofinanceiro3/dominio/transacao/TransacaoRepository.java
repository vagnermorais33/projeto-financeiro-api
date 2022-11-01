package com.financeiro.projetofinanceiro3.dominio.transacao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

}
