package com.marllonmendez.voll.domain.Colsulta.valid;

import com.marllonmendez.voll.domain.Colsulta.dto.DadosAgendamentoConsultaDTO;
import com.marllonmendez.voll.infra.exception.ValidException;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioClinicaValid implements AgendamentoConsultaValid {

    public void validar(DadosAgendamentoConsultaDTO dto) {
        var dataConsulta = dto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidException("Consulta fora do horário de funcionamento da clínica.");
        }
    }

}
