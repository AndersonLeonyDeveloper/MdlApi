package com.example.mdl.api.entities;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class MoradorDTO {

    private Long id;

    private String nome;

    private Boolean inadimplente;

    private BlocoApto blocoApto;

    private List<CarroDTO> carros;

    private List<TelefoneDTO> telefones;

    public MoradorDTO(Morador morador, List<CarroDTO> carros, List<TelefoneDTO> telefones) {
        this.id = morador.getId();
        this.nome = morador.getNome();
        this.inadimplente = morador.getInadimplente();
        this.blocoApto = morador.getBlocoApto();
        this.carros = carros;
        this.telefones = telefones;
    }

    public MoradorDTO() {
    }
}
