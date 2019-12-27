package com.example.mdl.api.repositories;

import com.example.mdl.api.entities.BlocoApto;
import org.springframework.data.repository.CrudRepository;


public interface BlocoAptoRepository extends CrudRepository<BlocoApto, Long> {
    BlocoApto findByBlocoAndApto(Long bloco, Long apto);
}
