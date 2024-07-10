package com.marllonmendez.vollmed.domain.Colsulta.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marllonmendez.vollmed.domain.Medico.entity.Medico;
import com.marllonmendez.vollmed.domain.Paciente.entity.Paciente;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
@Entity(name = "Consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;

    boolean disponivel;

    public void remover() {
        this.disponivel = false;
    }

}

