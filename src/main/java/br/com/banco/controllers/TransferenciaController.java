package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.ContaRepository;
import br.com.banco.repositorys.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping(value = "/transferencias/{id_conta}")
    public ResponseEntity<List<Transferencia>> transferencias(
            @PathVariable("id_conta") Long idConta,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataFinal,
            @RequestParam(required = false) String nomeOperadorTransacao

    ){
        List<Transferencia> transferencias;
        Conta conta;

        if(idConta != null){
            conta = contaRepository.findById(idConta).orElse(null);

            if (dataInicio != null && dataFinal != null && nomeOperadorTransacao != null){
                transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetweenAndNomeOperadorTransacao(conta, dataInicio, dataFinal, nomeOperadorTransacao);
                return ResponseEntity.ok(transferencias);
            }

            else if (dataInicio != null && dataFinal != null) {
                transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetween(conta, dataInicio, dataFinal);
                return ResponseEntity.ok(transferencias);
            }

            if(nomeOperadorTransacao != null){
                transferencias = transferenciaRepository.findByContaAndNomeOperadorTransacao(conta, nomeOperadorTransacao);
                return ResponseEntity.ok(transferencias);
            }

            return ResponseEntity.ok(transferenciaRepository.findByConta(conta));
        }

        return ResponseEntity.notFound().build();
    }

}


