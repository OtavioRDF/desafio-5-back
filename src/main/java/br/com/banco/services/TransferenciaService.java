package br.com.banco.services;

import br.com.banco.mappers.TransferenciaMapper;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.ContaRepository;
import br.com.banco.repositorys.TransferenciaRepository;
import br.com.banco.response.TransferenciaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    private Conta conta;
    private List<Transferencia> transferencias;
    private List<TransferenciaResponseDTO> transferenciaDto;

    public List<TransferenciaResponseDTO> findByAllArgs(Long idConta, String dataInicio, String dataFinal, String nomeOperadorTransacao){
        conta = contaRepository.findById(idConta).orElse(null);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio, dateFormatter);
        LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, dateFormatter);

        transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetweenAndNomeOperadorTransacao(conta, dataInicioLocalDate, dataFinalLocalDate, nomeOperadorTransacao);

        Float valorTotal = transferenciaRepository.selectValor(idConta);

        transferenciaDto = transferencias.stream()
                .map(transferencia -> TransferenciaMapper.toTransferenciaResponseDTO(transferencia, valorTotal))
                .collect(Collectors.toList());

        return transferenciaDto;
    }

    public List<TransferenciaResponseDTO> findByDateBetween(Long idConta, String dataInicio, String dataFinal){
        conta = contaRepository.findById(idConta).orElse(null);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio, dateFormatter);
        LocalDate dataFinalLocalDate = LocalDate.parse(dataFinal, dateFormatter);

        transferencias = transferenciaRepository.findByContaAndDataTransferenciaBetween(conta, dataInicioLocalDate, dataFinalLocalDate);

        Float valorTotal = transferenciaRepository.selectValor(idConta);

        transferenciaDto = transferencias.stream()
                .map(transferencia -> TransferenciaMapper.toTransferenciaResponseDTO(transferencia, valorTotal))
                .collect(Collectors.toList());

        return transferenciaDto;
    }

    public List<TransferenciaResponseDTO> findByNome(Long idConta, String nomeOperadorTransacao){
        conta = contaRepository.findById(idConta).orElse(null);

        transferencias = transferenciaRepository.findByContaAndNomeOperadorTransacao(conta, nomeOperadorTransacao);
        Float valorTotal = transferenciaRepository.selectValor(idConta);

        transferenciaDto = transferencias.stream()
                .map(transferencia -> TransferenciaMapper.toTransferenciaResponseDTO(transferencia, valorTotal))
                .collect(Collectors.toList());

        return transferenciaDto;
    }

    public List<TransferenciaResponseDTO> findByConta(Long idConta){
        conta = contaRepository.findById(idConta).orElse(null);

        transferencias = transferenciaRepository.findByConta(conta);
        Float valorTotal = transferenciaRepository.selectValor(idConta);

        transferenciaDto = transferencias.stream()
                .map(transferencia -> TransferenciaMapper.toTransferenciaResponseDTO(transferencia, valorTotal))
                .collect(Collectors.toList());

        return transferenciaDto;
    }

}
