package com.marllonmendez.voll.dto;

import com.marllonmendez.voll.enums.Ramo;
import com.marllonmendez.voll.modal.Endereco;
import com.marllonmendez.voll.modal.Medico;

public record DadosDetalhadosMedicoDTO(Long id, String nome, String email, String telefone, String crm, Ramo ramo, Endereco endereco) {

    public DadosDetalhadosMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getRamo(), medico.getEndereco());
    }
}
