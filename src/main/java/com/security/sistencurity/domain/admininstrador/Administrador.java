package com.security.sistencurity.domain.admininstrador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@AllArgsConstructor
@Getter
@Setter
public class Administrador {
    @Id
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String numeroTelefone;
    private LocalTime horarioChegada;
    private LocalTime horarioSaida;
    private Boolean ativo;
    private LocalDate dataAdmissao;
public Administrador(Long id,AdministradorDTO dto){
    this.id = id;
    this.nomeCompleto = dto.nome();
    this.cpf= dto.cpf();
    this.numeroTelefone= dto.telefone();
    this.horarioChegada = dto.comecoExpediente();
    this.horarioSaida = dto.fimExpediente();
    this.ativo = true;
    this.dataAdmissao = LocalDate.now();
}

}
