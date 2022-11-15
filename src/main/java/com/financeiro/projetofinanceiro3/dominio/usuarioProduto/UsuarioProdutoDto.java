package com.financeiro.projetofinanceiro3.dominio.usuarioProduto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioProdutoDto {

    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotBlank
    // @Size(max = 150)
    private String nome;
    private String sobrenome;

    @NotBlank
    private String username;

    // @Min(11)
    // @Max(13)
    @NotBlank
    @Length(min = 11, max = 13)
    private String cpf;

}