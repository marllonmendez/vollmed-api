package com.marllonmendez.vollmed.domain.Medico.dto;

import com.marllonmendez.vollmed.domain.Endereco.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoDadosMedicoDTO(
        @NotNull
        Long Id,
        String nome,
        String telefone,
        EnderecoDTO endereco
) {}
