package br.com.banco.repositorys;

import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(OffsetDateTime dataInicio, OffsetDateTime dataFinal, String nomeOperador);
    List<Transferencia> findByDataTransferenciaBetween(OffsetDateTime dataInicio, OffsetDateTime dataFinal);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperador);
}
