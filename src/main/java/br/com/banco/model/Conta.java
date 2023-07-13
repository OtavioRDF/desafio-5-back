package br.com.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "conta")
public class Conta {
    @Id
    private Long idConta;

    private String nomeResponsavel;
}