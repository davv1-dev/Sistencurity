package com.security.sistencurity.domain.morador;

import com.security.sistencurity.domain.visitante.Visitante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "morador")
@Table(name = "moradores")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Morador {
    @Id
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String numeroTelefone;
    private String torre;
    private String apartamento;
    @OneToMany(mappedBy = "morador",
    cascade = CascadeType.ALL)
    private List<Visitante> visitantes;

    public Morador(MoradorDTO dados){
        this.nomeCompleto = dados.nomeCompleto();
        this.cpf = dados.cpf();
        this.numeroTelefone = dados.numeroTelefone();
        this.torre = dados.torre();
        this.apartamento = dados.apartamento();
    }
}
