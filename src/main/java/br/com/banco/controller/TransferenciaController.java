package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository trasnferencia;

    @GetMapping(value = "/teste")
    public List<Transferencia> Hello(){
        return trasnferencia.findAll();
    }
}
