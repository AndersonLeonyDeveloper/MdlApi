package com.example.mdl.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "telefone")
@Data
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tel;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Morador morador;

}