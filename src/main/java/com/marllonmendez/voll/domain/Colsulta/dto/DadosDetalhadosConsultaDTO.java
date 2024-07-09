package com.marllonmendez.voll.domain.Colsulta.dto;

import com.marllonmendez.voll.domain.Colsulta.entity.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhadosConsultaDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public DadosDetalhadosConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

}
