package br.com.banco.repositorys;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaAndDataTransferenciaBetweenAndNomeOperadorTransacao(Conta conta, Date dataInicio, Date dataFinal, String nomeOperador);
    List<Transferencia> findByContaAndDataTransferenciaBetween(Conta conta, Date dataInicio, Date dataFinal);
    List<Transferencia> findByContaAndNomeOperadorTransacao(Conta conta, String nomeOperador);

    List<Transferencia> findByConta(Conta conta);
}
