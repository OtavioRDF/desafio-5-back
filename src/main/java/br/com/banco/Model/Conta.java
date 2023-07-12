package br.com.banco.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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