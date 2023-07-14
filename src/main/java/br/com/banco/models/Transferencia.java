package br.com.banco.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataTransferencia;

    private float valor;

    private String tipo;

    private String nomeOperadorTransacao;

    @OneToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;
}
