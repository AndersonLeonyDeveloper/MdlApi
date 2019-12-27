package com.example.mdl.api.repositories;

import com.example.mdl.api.entities.Carro;
import com.example.mdl.api.entities.Telefone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends CrudRepository<Carro, Long> {

    List<Carro> findByMoradorId(Long morador_id);

    Optional<Carro> findByIdAndMoradorId(Long id, Long morador_id);

}
