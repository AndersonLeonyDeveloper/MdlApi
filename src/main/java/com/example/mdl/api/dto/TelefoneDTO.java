package com.example.mdl.api.dto;

import com.example.mdl.api.entities.Telefone;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TelefoneDTO {

    private Long id;

    private String tel;

    public static TelefoneDTO create(Telefone telefone) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(telefone, TelefoneDTO.class);

    }
}
