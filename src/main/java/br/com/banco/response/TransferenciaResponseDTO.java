package br.com.banco.response;

import br.com.banco.models.Conta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class TransferenciaResponseDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataTransferencia;

    private float valor;

    private String tipo;

    private String nomeOperadorTransacao;

    private Conta conta;

    private Float valorTotal;
}
