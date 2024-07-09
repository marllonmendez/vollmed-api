package com.marllonmendez.voll.domain.Medico.dto;

import com.marllonmendez.voll.domain.Endereco.dto.EnderecoDTO;
import com.marllonmendez.voll.enums.Ramo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{8,10}")
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Ramo ramo,

        @NotNull
        @Valid
        EnderecoDTO endereco
) {}
