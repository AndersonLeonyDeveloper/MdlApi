package com.example.mdl.api.services;

import com.example.mdl.api.entities.*;
import com.example.mdl.api.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository rep;

    public List<TelefoneDTO> getListOfTelefonesByMoradorId(Long moradorId){

        List<TelefoneDTO> telefonesDTOlist = new ArrayList<>();

        rep.findByMoradorId(moradorId).forEach(telefone -> {
            telefonesDTOlist.add(new TelefoneDTO(telefone));
        });

        return telefonesDTOlist;
    }

    public Telefone insertOrUpdate(TelefoneDTO telefone, Morador morador){
        Optional<Telefone> optional = getTelefoneByIdAndMoradorId(telefone.getId(), morador.getId());

        if(optional.isPresent()) {
            Telefone telefoneDb = optional.get();

            telefoneDb.setTel(telefone.getTel());

            return rep.save(telefoneDb);
        } else {
            Telefone telefoneAdd = new Telefone();
            telefoneAdd.setTel(telefone.getTel());
            telefoneAdd.setMorador(morador);

            return rep.save(telefoneAdd);
        }
    }

    public void insertOrUpdateList(List<TelefoneDTO> telefones, Morador morador) {
        List<Telefone> telefonesListUpdate = new ArrayList<>();

        telefones.forEach(telefone -> {
            telefonesListUpdate.add(new Telefone(telefone.getId(), telefone.getTel(), morador));
        });
        rep.deleteAll();
        rep.saveAll(telefonesListUpdate);

//        telefones.forEach(telefone -> {
////            insert(telefone, morador);
//            insertOrUpdate(telefone, morador);
//        });
    }

    public Optional<Telefone> getTelefoneById(Long id) {
        return rep.findById(id);
    }

    public Optional<Telefone> getTelefoneByIdAndMoradorId(Long id, Long moradorId) {
        return rep.findByIdAndMoradorId(id, moradorId);
    }

    public void updateList(List<TelefoneDTO> telefones, Morador morador) {
        telefones.forEach(telefone -> {
//            if(telefone.getId()!=null) {
//                update(telefone, morador);
//            } else {
//                insert(telefone, morador);
//            }
            insertOrUpdate(telefone, morador);
        });
    }

}
