package com.marllonmendez.vollmed.domain.Paciente.valid;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.vollmed.domain.Paciente.repository.IPacienteRepository;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteDisponivelValid implements AgendamentoConsultaValid {

    @Autowired
    private IPacienteRepository iPacienteRepository;

    public void validar(AgendamentoConsultaDTO dto) {
        var pacienteDisponivel = iPacienteRepository.findDisponivelById(dto.idPaciente());
        if (!pacienteDisponivel) {
            throw new ValidException("Consulta não pode ser agendada com paciente indisponível!");
        }
    }

}
