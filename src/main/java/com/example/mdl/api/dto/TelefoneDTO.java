package com.example.mdl.api.dto;

import com.example.mdl.api.entities.Telefone;
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
