package com.marllonmendez.vollmed.domain.Medico.dto;

import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.enums.Ramo;

public record DadosMedicoDTO(Long id, String nome, String email, String crm, Ramo ramo) {

    public DadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getRamo());
    }

}
