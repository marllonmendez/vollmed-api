package com.marllonmendez.voll.domain.Medico.dto;

import com.marllonmendez.voll.domain.Endereco.entity.Endereco;
import com.marllonmendez.voll.domain.Medico.entity.Medico;
import com.marllonmendez.voll.enums.Ramo;

public record DadosDetalhadosMedicoDTO(Long id, String nome, String email, String telefone, String crm, Ramo ramo, Endereco endereco) {

    public DadosDetalhadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getRamo(), medico.getEndereco());
    }

}
