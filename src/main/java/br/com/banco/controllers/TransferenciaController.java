package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.ContaRepository;
import br.com.banco.repositorys.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dataFinal,
            @RequestParam(required = false) String nomeOperadorTransacao

    ){
        List<Transferencia> transferencias;
        Conta conta;

        if(idConta != null){
            conta = contaRepository.findById(idConta).orElse(null);

            if (dataInicio != null && dataFinal != null && nomeOperadorTransacao != null){
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio, dateFormatter);
                LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, dateFormatter);

                transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetweenAndNomeOperadorTransacao(conta, dataInicioLocalDate, dataFinalLocalDate, nomeOperadorTransacao);
                return ResponseEntity.ok(transferencias);
            }

           else if (dataInicio != null && dataFinal != null) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio, dateFormatter);
                LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, dateFormatter);
                transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetween(conta, dataInicioLocalDate, dataFinalLocalDate);
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


