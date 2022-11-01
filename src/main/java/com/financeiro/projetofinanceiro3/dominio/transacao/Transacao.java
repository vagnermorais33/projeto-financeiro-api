package com.financeiro.projetofinanceiro3.dominio.transacao;

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
import com.financeiro.projetofinanceiro3.dominio.usuario.Usuario;
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
public class Transacao extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updateaAt;

    @Column(nullable = false)
    private String tipo;
    private Double valor;
    private Double saldo;
    private String descricao;

    @ManyToOne
    @NotNull

    private Usuario usuario;

}