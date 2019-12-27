package com.example.mdl.api.services;

import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository rep;

    public Optional<Morador> getMoradorById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Morador> getMoradores(){
        return rep.findAll();
    }
}
