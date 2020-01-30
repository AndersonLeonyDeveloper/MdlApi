package com.example.mdl.api.entities;

import lombok.Data;

@Data
public class TelefoneDTO {

    private Long id;

    private String tel;

    public TelefoneDTO(Telefone tel) {
        this.id = tel.getId();
        this.tel = tel.getTel();
    }

    public TelefoneDTO() {
    }
}
