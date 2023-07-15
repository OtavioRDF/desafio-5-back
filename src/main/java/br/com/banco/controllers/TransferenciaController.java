package br.com.banco.controllers;

import br.com.banco.response.TransferenciaResponseDTO;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @GetMapping(value = "/transferencias/{id_conta}")
    public ResponseEntity<List<TransferenciaResponseDTO>> transferencias(
            @PathVariable("id_conta") Long idConta,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dataFinal,
            @RequestParam(required = false) String nomeOperadorTransacao

    ){
        List<TransferenciaResponseDTO> response;

        if(idConta != null){

            if (dataInicio != null && dataFinal != null && nomeOperadorTransacao != null){
                response = service.findByAllArgs(idConta, dataInicio, dataFinal, nomeOperadorTransacao);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

           else if (dataInicio != null && dataFinal != null) {
                response = service.findByDateBetween(idConta, dataInicio, dataFinal);

                return ResponseEntity.status(HttpStatus.OK).body(response);

            }

            if(nomeOperadorTransacao != null){
                response = service.findByNome(idConta,nomeOperadorTransacao);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            return ResponseEntity.ok(service.findByConta(idConta));
        }

        return ResponseEntity.notFound().build();
    }
}



