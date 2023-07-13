package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @Column(name = "id_conta")
    private Long idConta;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;
}