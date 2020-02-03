package com.example.mdl.api.controllers;

import com.example.mdl.api.dto.CarroDTO;
import com.example.mdl.api.dto.MoradorDTO;
import com.example.mdl.api.entities.*;
import com.example.mdl.api.services.BlocoAptoService;
import com.example.mdl.api.services.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<MoradorDTO>> get() {
        List<MoradorDTO> carrosDTO = moradorService.getMoradores();

        return carrosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(moradorService.getMoradores());

//        return ResponseEntity.ok(moradorService.getMoradores());
//        return new ResponseEntity<>(moradorService.getMoradores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        MoradorDTO moradorDTO = moradorService.getMoradorById(id);
        return ResponseEntity.ok(moradorDTO);
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
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody MoradorDTO moradorDTO) {

        MoradorDTO m = moradorService.insert(moradorDTO);
        URI location = getUri(m.getId());
        return ResponseEntity.created(location).build();

    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/editarMorador/{id}")
    public ResponseEntity put(@PathVariable("id") Long moradorId, @RequestBody MoradorDTO moradorDTO) {
        moradorDTO.setId(moradorId);
        MoradorDTO m = moradorService.update(moradorDTO, moradorId);

        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){

        moradorService.delete(id);

        return ResponseEntity.ok().build();
    }

}
