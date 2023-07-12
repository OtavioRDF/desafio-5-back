package br.com.banco.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_conta")
    private long idConta;

    @Column(nullable = false)
    private String nomeResponsavel;
}