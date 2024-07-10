package com.marllonmendez.vollmed.domain.Paciente.dto;

import com.marllonmendez.vollmed.domain.Paciente.entity.Paciente;

public record ListagemPacienteDTO(Long id, String nome, String email, String cpf) {

    public ListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
