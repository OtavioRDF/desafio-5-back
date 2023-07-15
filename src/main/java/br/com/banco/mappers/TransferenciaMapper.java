package br.com.banco.mappers;

import br.com.banco.models.Transferencia;
import br.com.banco.response.TransferenciaResponseDTO;


public class TransferenciaMapper {

    public static TransferenciaResponseDTO toTransferenciaResponseDTO(Transferencia transferencias, Float valorTotal){
        TransferenciaResponseDTO transferenciaDTO = TransferenciaResponseDTO.builder()
                .id(transferencias.getId())
                .dataTransferencia(transferencias.getDataTransferencia())
                .valor(transferencias.getValor())
                .tipo(transferencias.getTipo())
                .nomeOperadorTransacao(transferencias.getNomeOperadorTransacao())
                .conta(transferencias.getConta())
                .valorTotal(valorTotal)
                .build();

        return  transferenciaDTO;
    }
}
