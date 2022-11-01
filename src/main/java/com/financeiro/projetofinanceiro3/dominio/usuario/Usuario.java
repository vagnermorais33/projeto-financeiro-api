package com.financeiro.projetofinanceiro3.dominio.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.financeiro.projetofinanceiro3.dominio.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario extends Model implements Serializable {

    @Column(nullable = false)
    private String nome;

    private String sobrenome;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String cpf;
}
