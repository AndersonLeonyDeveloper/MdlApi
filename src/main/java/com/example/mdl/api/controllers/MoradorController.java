package com.example.mdl.api.controllers;

import com.example.mdl.api.dto.MoradorDTO;
import com.example.mdl.api.entities.*;
import com.example.mdl.api.services.BlocoAptoService;
import com.example.mdl.api.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @Autowired
    private BlocoAptoService blocoAptoService;

    @GetMapping()
    public Iterable<MoradorDTO> get() {
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
    public String put(@PathVariable("id") Long moradorId, @RequestBody MoradorDTO moradorDTO) {
        Morador m = moradorService.update(moradorDTO, moradorId);

        return "Morador editado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){

        moradorService.delete(id);

        return "Morador deletado com sucesso.";
    }

}
