package com.security.sistencurity.domain.visitante;

import com.security.sistencurity.domain.morador.Morador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;


@Entity(name = "visitante")
@Table(name = "visitantes")
@AllArgsConstructor
@Getter
@Setter
public class Visitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompletoVisitante;
    private String nomeCompletoMorador;
    private String cpf;
    private Instant dataVisita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "morador_id",nullable = false)
    private Morador morador;
    public Visitante(VisitanteDTO visitante,Morador morador){
        this.nomeCompletoVisitante = visitante.nome();
        this.nomeCompletoMorador = morador.getNomeCompleto();
        this.cpf = visitante.cpf();
        this.morador = morador;
    }

}
