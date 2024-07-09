package com.marllonmendez.voll.domain.Paciente.valid;

import com.marllonmendez.voll.domain.Colsulta.dto.DadosAgendamentoConsultaDTO;
import com.marllonmendez.voll.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.voll.domain.Paciente.repository.IPacienteRepository;
import com.marllonmendez.voll.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteDisponivelValid implements AgendamentoConsultaValid {

    @Autowired
    private IPacienteRepository iPacienteRepository;

    public void validar(DadosAgendamentoConsultaDTO dto) {
        var pacienteDisponivel = iPacienteRepository.findDisponivelById(dto.idPaciente());
        if (!pacienteDisponivel) {
            throw new ValidException("Consulta não pode ser agendada com paciente indisponível!");
        }
    }

}
