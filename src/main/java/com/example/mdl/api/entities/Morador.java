package com.example.mdl.api.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "morador")
@Data
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Boolean indadimplente;

    @ManyToOne
    @JoinColumn(name = "bloco_apto_id")
    private BlocoApto blocoApto;

    @OneToMany
    @JoinColumn(name = "morador_id")
    private List<Carro> carros;

    @OneToMany
    @JoinColumn(name = "morador_id")
    private List<Telefone> telefones;

}