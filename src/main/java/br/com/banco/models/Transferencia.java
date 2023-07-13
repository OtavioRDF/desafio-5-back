package br.com.banco.models;

import lombok.Data;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
public class Transferencia {
    @Id
    private Long id;

    private OffsetDateTime dataTransferencia;

    private float valor;

    private String tipo;

    private String nomeOperadorTransacao;

    @OneToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;
}
