package com.example.mdl.api.controllers;

import com.example.mdl.api.entities.*;
import com.example.mdl.api.services.BlocoAptoService;
import com.example.mdl.api.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @Autowired
    private BlocoAptoService blocoAptoService;

    @GetMapping()
    public Iterable<Morador> get() {
        return moradorService.getMoradores();
    }

    @GetMapping("/{id}")
    public MoradorDTO get(@PathVariable("id") Long id) {
        return moradorService.getMoradorById(id);
    }

    @GetMapping("/inadimplentes/{situacao}")
    public Iterable<Morador> getMoradorBySituacao(@PathVariable("situacao") String situacao) {
        if (situacao.equalsIgnoreCase("sim")) {
            return moradorService.getMoradorBySituacao(Boolean.TRUE);
        } else if (situacao.equalsIgnoreCase("nao")) {
            return moradorService.getMoradorBySituacao(Boolean.FALSE);
        } else {
            return null;
        }

    }

    @PostMapping("/adicionarMorador")
    public String post(@RequestBody MoradorDTO moradorDTO) {

        MoradorDTO m = moradorService.insert(moradorDTO);

        return "Morador salvo com sucesso - "+m.getNome();
    }

    @PutMapping("/editarMorador/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Morador morador) {
        Morador m = moradorService.update(morador, id);

        return "Morador editado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){

        moradorService.delete(id);

        return "Morador deletado com sucesso.";
    }

}
