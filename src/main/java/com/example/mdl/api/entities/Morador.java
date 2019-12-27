package com.example.mdl.api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private Boolean inadimplente;

    @ManyToOne
    @JoinColumn(name = "bloco_apto_id")
    private BlocoApto blocoApto;

}