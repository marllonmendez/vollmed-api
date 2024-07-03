package com.marllonmendez.voll.modal;

import com.marllonmendez.voll.dto.AtualizacaoDadosMedicoDTO;
import com.marllonmendez.voll.dto.MedicoDTO;
import com.marllonmendez.voll.enums.Ramo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medicos")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Ramo ramo;

    @Embedded
    private Endereco endereco;

    private Boolean disponivel;

    public Medico(MedicoDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.crm = dto.crm();
        this.ramo = dto.ramo();
        this.endereco = new Endereco(dto.endereco());
        this.disponivel = true;
    }

    public void atualizarMedico(AtualizacaoDadosMedicoDTO dto) {
        this.nome = dto.nome() != null ? dto.nome() : this.nome;
        this.telefone = dto.telefone() != null ? dto.telefone() : this.telefone;
        if (dto.endereco() != null) {
            this.endereco.atualizarEndereco(dto.endereco());
        }
    }

    public void remover() {
        this.disponivel = false;
    }

}
