package com.example.mdl.api.entities;

import lombok.Data;

import java.util.List;

@Data
public class MoradorDTO {

    private Long id;

    private String nome;

    private Boolean inadimplente;

    private BlocoApto blocoApto;

    private List<Carro> carros;

    private List<Telefone> telefones;

}
