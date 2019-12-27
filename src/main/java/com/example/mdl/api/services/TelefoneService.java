package com.example.mdl.api.services;

import com.example.mdl.api.entities.Carro;
import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.entities.Telefone;
import com.example.mdl.api.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository rep;

    public List<Telefone> getListOfTelefonesByMoradorId(Long morador_id){

        return rep.findByMoradorId(morador_id);
    }

    public Telefone insert(Telefone telefone, Morador morador) {
        Assert.notNull(telefone.getId(), "Não foi possível atualizar o registro.");

        Telefone telefoneAdd = new Telefone();
        telefoneAdd.setTel(telefone.getTel());
        telefoneAdd.setMorador(morador);

        return rep.save(telefone);
    }

    public Telefone update(Telefone telefone, Long id) {

        System.out.println(id);
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Telefone> optional = getTelefoneById(id);

        if(optional.isPresent()) {
            Telefone telefoneDb = optional.get();

            telefoneDb.setTel(telefone.getTel());

            rep.save(telefoneDb);

            return telefoneDb;

        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }

    }

    public Optional<Telefone> getTelefoneById(Long id) {
        return rep.findById(id);
    }

    public Optional<Telefone> getTelefoneByIdAndMoradorId(Long id, Long morador_id) {
        return rep.findByIdAndMoradorId(id, morador_id);
    }

    public void updateList(List<Telefone> telefones) {
        telefones.forEach(telefone -> {
            update(telefone, telefone.getId());
        });
    }

    public void insertList(List<Telefone> telefones, Morador morador) {
        telefones.forEach(telefone -> {
            insert(telefone, morador);
        });
    }
}
