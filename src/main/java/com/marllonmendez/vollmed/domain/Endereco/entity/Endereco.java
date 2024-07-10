package com.marllonmendez.vollmed.domain.Endereco.entity;

import com.marllonmendez.vollmed.domain.Endereco.dto.EnderecoDTO;

import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.cep = dto.cep();
    }

    public void atualizarEndereco(EnderecoDTO dto) {
     this.logradouro = dto.logradouro() != null ? dto.logradouro() : this.logradouro;
     this.numero = dto.numero() != null ? dto.numero() : this.numero;
     this.complemento = dto.complemento() != null ? dto.complemento() : this.complemento;
     this.bairro = dto.bairro() != null ? dto.bairro() : this.bairro;
     this.cidade = dto.cidade() != null ? dto.cidade() : this.cidade;
     this.uf = dto.uf() != null ? dto.uf() : this.uf;
     this.cep = dto.cep() != null ? dto.cep() : this.cep;
    }

}
