package com.marllonmendez.voll.domain.Medico.valid;

import com.marllonmendez.voll.domain.Colsulta.dto.DadosAgendamentoConsultaDTO;
import com.marllonmendez.voll.domain.Colsulta.valid.AgendamentoConsultaValid;
import com.marllonmendez.voll.domain.Medico.repository.IMedicoRepository;
import com.marllonmendez.voll.infra.exception.ValidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoDisponivelValid implements AgendamentoConsultaValid {

    @Autowired
    private IMedicoRepository iMedicoRepository;

    public void validar(DadosAgendamentoConsultaDTO dto) {
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
