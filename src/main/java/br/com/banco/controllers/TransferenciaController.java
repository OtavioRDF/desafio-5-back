package br.com.banco.controllers;

import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository transferencia;

    @GetMapping(value = "/transferencias")
    public ResponseEntity<List<Transferencia>> findAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataFinal,
            @RequestParam(required = false) String nomeOperadorTransacao

    ){
        List<Transferencia> transferencias;

        if (dataInicio != null && dataFinal != null && nomeOperadorTransacao != null){
            transferencias = transferencia.findByDataTransferenciaBetweenAndNomeOperadorTransacao(dataInicio, dataFinal, nomeOperadorTransacao);
            return ResponseEntity.ok(transferencias);
        }

        else if (dataInicio != null && dataFinal != null) {
            transferencias = transferencia.findByDataTransferenciaBetween(dataInicio, dataFinal);
            return ResponseEntity.ok(transferencias);
        }

        if(nomeOperadorTransacao != null){
            transferencias = transferencia.findByNomeOperadorTransacao(nomeOperadorTransacao);
            return ResponseEntity.ok(transferencias);
        }


        return ResponseEntity.ok(transferencia.findAll());
    }

}


