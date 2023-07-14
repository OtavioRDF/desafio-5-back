package br.com.banco.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;

    private String nomeResponsavel;
}