package br.com.banco.model;

import lombok.Data;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transferencia", nullable = false)
    private OffsetDateTime dataTransferencia;

    @Column(nullable = false, precision = 20, scale = 2)
    private float valor;

    @Column(nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;
}
