package com.marllonmendez.vollmed.domain.Colsulta.valid;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAtencedenciaValid implements AgendamentoConsultaValid {

    public void validar(AgendamentoConsultaDTO dto) {
        var dataConsulta = dto.data();
        var dataAgora = LocalDateTime.now();
        var diferancaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferancaEmMinutos < 30) {
            throw new ValidException("Consulta deve ser agendada com atecedência mínima de 30 minutos!");
        }
    }

}
