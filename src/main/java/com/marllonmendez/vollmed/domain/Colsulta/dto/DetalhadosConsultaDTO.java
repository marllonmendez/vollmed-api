package com.marllonmendez.vollmed.domain.Colsulta.dto;

import com.marllonmendez.vollmed.domain.Colsulta.entity.Consulta;

import java.time.LocalDateTime;

public record DetalhadosConsultaDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DetalhadosConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

}
