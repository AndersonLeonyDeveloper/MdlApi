package com.example.mdl.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "carro")
@Data
@DynamicUpdate
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Long id;

    @Column(unique = true)
    private String placa;

    private String modelo;

    private String cor;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Morador morador;

    public Carro(Long id, String placa, String modelo, String cor, Morador morador) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.morador = morador;
    }

    public Carro() {
    }
}