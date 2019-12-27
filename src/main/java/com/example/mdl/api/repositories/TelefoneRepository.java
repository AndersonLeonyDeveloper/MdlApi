package com.example.mdl.api.repositories;

import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.entities.Telefone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

    List<Telefone> findByMoradorId(Long morador_id);

    Optional<Telefone> findByIdAndMoradorId(Long id, Long morador_id);

}
