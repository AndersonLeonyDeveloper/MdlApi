package com.example.mdl.api.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bloco_apto")
public class BlocoApto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bloco;

    private Long apto;
}
