package com.marllonmendez.voll.dto;

import com.marllonmendez.voll.enums.Ramo;
import com.marllonmendez.voll.modal.Medico;

public record DadosMedicoDTO(Long id, String nome, String email, String crm, Ramo ramo) {

    public DadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getRamo());
    }

}
