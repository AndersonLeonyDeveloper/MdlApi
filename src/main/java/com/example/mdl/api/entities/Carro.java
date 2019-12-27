package com.example.mdl.api.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carro")
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String modelo;

    private String cor;

//    @ManyToOne
//    private Morador morador;

}