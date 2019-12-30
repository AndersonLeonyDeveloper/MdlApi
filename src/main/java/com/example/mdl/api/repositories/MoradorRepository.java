package com.example.mdl.api.repositories;

import com.example.mdl.api.entities.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {

    Iterable<Morador> findByInadimplente(Boolean situacao);
}
