package com.marllonmendez.voll.domain.Medico.dto;

import com.marllonmendez.voll.domain.Medico.entity.Medico;
import com.marllonmendez.voll.enums.Ramo;

public record DadosMedicoDTO(Long id, String nome, String email, String crm, Ramo ramo) {

    public DadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getRamo());
    }

}
