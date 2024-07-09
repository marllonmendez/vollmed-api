package com.marllonmendez.voll.domain.Medico.valid;

import com.marllonmendez.voll.domain.Colsulta.dto.DadosAgendamentoConsultaDTO;
import com.marllonmendez.voll.domain.Colsulta.repository.IConsultaRepository;
import com.marllonmendez.voll.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.voll.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConsultaMesmaHoraValid implements AgendamentoConsultaValid {

    @Autowired
    private IConsultaRepository iConsultaRepository;

    public void validar(DadosAgendamentoConsultaDTO dto) {
        var medicoConsultaMesmaHora = iConsultaRepository.existsByMedicoIdAndData(dto.idMedico(), dto.data());
        if (medicoConsultaMesmaHora) {
            throw new ValidException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }

}
