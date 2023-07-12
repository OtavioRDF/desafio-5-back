package br.com.banco.Model;

import lombok.Data;
import lombok.Generated;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transferencia {
    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private Date dataTransferencia;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String tipo;

    @Column
    private String nomeOperadorTransacao;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Transferencia contaId;
}
