package br.com.banco.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Conta {
    @Id
    private Long idConta;

    private String nomeResponsavel;
}