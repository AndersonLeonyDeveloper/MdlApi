package com.example.mdl.api.services;

import com.example.mdl.api.entities.Carro;
import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<Carro> getListOfCarrosByMoradorId(Long morador_id){

        return rep.findByMoradorId(morador_id);
    }

    public Carro insertOrUpdate(Carro carro, Morador morador){
        Optional<Carro> optional = getCarroByIdAndMoradorId(carro.getId(), morador.getId());

        if(optional.isPresent()) {
            Carro carroUpdate = optional.get();

            carroUpdate.setModelo(carro.getModelo());
            carroUpdate.setCor(carro.getCor());
            carroUpdate.setPlaca(carro.getPlaca());

            return rep.save(carroUpdate);

        } else {

            Carro carroInsert = new Carro();
            carroInsert.setPlaca(carro.getPlaca());
            carroInsert.setCor(carro.getCor());
            carroInsert.setModelo(carro.getModelo());
            carroInsert.setMorador(morador);

            return rep.save(carroInsert);
        }
    }

    public void insertOrUpdateList(List<Carro> carros, Morador morador) {
        carros.forEach(carro -> {
            insertOrUpdate(carro, morador);
        });
    }

    public Optional<Carro> getCarroById(Long id) {
        return rep.findById(id);
    }

    public Optional<Carro> getCarroByIdAndMoradorId(Long id, Long morador_id) {
        return rep.findByIdAndMoradorId(id, morador_id);
    }

}
