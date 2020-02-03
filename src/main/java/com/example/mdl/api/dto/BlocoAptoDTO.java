package com.example.mdl.api.dto;

import com.example.mdl.api.entities.BlocoApto;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class BlocoAptoDTO {

    private Long id;

    private Long bloco;

    private Long apto;

    public static BlocoAptoDTO create(BlocoApto blocoApto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(blocoApto, BlocoAptoDTO.class);
    }
}
