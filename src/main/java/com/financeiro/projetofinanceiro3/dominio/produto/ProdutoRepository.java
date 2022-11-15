package com.financeiro.projetofinanceiro3.dominio.produto;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
