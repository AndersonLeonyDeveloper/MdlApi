package com.example.mdl.api.entities;
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

//    @ManyToOne
//    private Morador morador;

}