package com.marllonmendez.voll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(

        @NotBlank
        String logradouro,

        String numero,
        String complemento,

        @NotBlank
        String bairro,

        @NotNull
        String cidade,

        @NotBlank
        String uf,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep

) {}
