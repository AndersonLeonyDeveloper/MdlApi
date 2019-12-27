package com.example.mdl.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "carro")
@Data
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

}