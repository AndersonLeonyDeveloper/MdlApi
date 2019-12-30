package com.example.mdl.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "telefone")
@Data
@DynamicUpdate
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tel;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Morador morador;

}