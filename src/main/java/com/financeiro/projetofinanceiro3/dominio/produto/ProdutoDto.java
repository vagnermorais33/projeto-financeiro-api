package com.financeiro.projetofinanceiro3.dominio.produto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.financeiro.projetofinanceiro3.dominio.usuarioProduto.UsuarioProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotBlank(message = " Nome não pode ser nulo ou vazio!")
    private String nome;

    @NotNull
    private Double valorCompra;

    private Double estoque;

    private String descricao;

    @NotNull
    private UsuarioProduto usuarioProduto;

}