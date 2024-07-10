package com.marllonmendez.vollmed.domain.Paciente.dto;

import com.marllonmendez.vollmed.domain.Endereco.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {}
