package com.marllonmendez.vollmed.domain.Medico.valid;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.vollmed.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConsultaMesmaHoraValid implements AgendamentoConsultaValid {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    public void validar(AgendamentoConsultaDTO dto) {
        var medicoConsultaMesmaHora = iConsultaRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (medicoConsultaMesmaHora) {
            throw new ValidException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }

}
