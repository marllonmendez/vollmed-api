package com.marllonmendez.voll.domain.Paciente.dto;

import com.marllonmendez.voll.domain.Paciente.entity.Paciente;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String cpf) {

    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
