package com.example.mdl.api.dto;

import com.example.mdl.api.entities.Carro;
import lombok.Data;

@Data
public class CarroDTO {

    private Long id;

    private String placa;

    private String modelo;

    private String cor;

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.placa = carro.getPlaca();
        this.modelo = carro.getModelo();
        this.cor = carro.getCor();
    }

    public CarroDTO() {
    }
}
