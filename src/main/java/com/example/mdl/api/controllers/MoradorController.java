package com.example.mdl.api.controllers;

import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/moradores")
public class MoradorController {

    @Autowired
    private MoradorService service;

//    @GetMapping()
//    public Iterable<Morador> get() {
//        return service.getMoradores();
//    }

    @GetMapping("/{id}")
    public Optional<Morador> get(@PathVariable("id") Long id) {
        return service.getMoradorById(id);
    }

}
