package com.financeiro.projetofinanceiro3.dominio.produto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.financeiro.projetofinanceiro3.dominio.model.Model;
import com.financeiro.projetofinanceiro3.dominio.usuarioProduto.UsuarioProduto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Produto extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updateaAt;

    @Column(nullable = false)
    private String nome;
    private Double valorCompra;
    private Double estoque;
    private String descricao;

    @ManyToOne
    @NotNull

    private UsuarioProduto usuarioProduto;

}
