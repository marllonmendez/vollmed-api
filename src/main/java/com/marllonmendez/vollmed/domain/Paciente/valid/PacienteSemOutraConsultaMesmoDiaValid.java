package com.marllonmendez.vollmed.domain.Paciente.valid;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.vollmed.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSemOutraConsultaMesmoDiaValid implements AgendamentoConsultaValid {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    public void validar(AgendamentoConsultaDTO dto) {
        var primeiroHorario = dto.data().withHour(7);
        var ultimoHorario = dto.data().withHour(18);
        var pacienteComOutraConsultaMesmoDia = iConsultaRepository.existsByPacienteIdAndDataBetween(dto.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacienteComOutraConsultaMesmoDia) {
            throw new ValidException("Paciente já possui uma consulta agendada nesse dia!");
        }
    }

}
