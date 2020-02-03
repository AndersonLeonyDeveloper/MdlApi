package com.example.mdl.api.services;

import com.example.mdl.api.dto.BlocoAptoDTO;
import com.example.mdl.api.entities.BlocoApto;
import com.example.mdl.api.repositories.BlocoAptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlocoAptoService {

    @Autowired
    private BlocoAptoRepository blocoAptoRepository;

    public BlocoApto getBlocoApto(Long bloco, Long apto){
//        return blocoAptoRepository.findByBlocoAndApto(bloco, apto);
        return blocoAptoRepository.findByBlocoAndApto(bloco, apto);
    }

}
