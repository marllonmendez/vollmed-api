package com.marllonmendez.vollmed.domain.Medico.valid;

import com.marllonmendez.vollmed.domain.Colsulta.dto.AgendamentoConsultaDTO;
import com.marllonmendez.vollmed.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.vollmed.domain.Medico.repository.IMedicoRepository;
import com.marllonmendez.vollmed.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoDisponivelValid implements AgendamentoConsultaValid {

    @Autowired
    private IMedicoRepository iMedicoRepository;

    public void validar(AgendamentoConsultaDTO dto) {
        // escolha do medico é opcional
        if (dto.idMedico() == null) {
            return;
        }

        var medicoDisponivel = iMedicoRepository.findDisponivelById(dto.idMedico());
        if (!medicoDisponivel) {
            throw new ValidException("Consulta não pode ser agendada com médico indisponível.");
        }
    }

}
