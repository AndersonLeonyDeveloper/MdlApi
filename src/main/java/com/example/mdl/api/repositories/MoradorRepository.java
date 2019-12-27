package com.example.mdl.api.repositories;

import com.example.mdl.api.entities.Morador;
import org.springframework.data.repository.CrudRepository;

public interface MoradorRepository extends CrudRepository<Morador, Long> {

    Iterable<Morador> findByInadimplente(Boolean situacao);
}
