package com.marllonmendez.vollmed.domain.Medico.dto;

import com.marllonmendez.vollmed.domain.Endereco.entity.Endereco;
import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.enums.Ramo;

public record DetalhadosMedicoDTO(Long id, String nome, String email, String telefone, String crm, Ramo ramo, Endereco endereco) {

    public DetalhadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getRamo(), medico.getEndereco());
    }

}
