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

    public List<Carro> getListOfCarsByMoradorId(Long morador_id){

        return rep.findByMoradorId(morador_id);
    }

    public Carro insert(Carro carro, Morador morador) {
        Assert.notNull(carro.getId(), "Não foi possível salvar o registro.");

        Carro carroAdd = new Carro();
        carroAdd.setPlaca(carro.getPlaca());
        carroAdd.setCor(carro.getCor());
        carroAdd.setModelo(carro.getModelo());
        carroAdd.setMorador(morador);

        return rep.save(carroAdd);
    }

    public void insertList(List<Carro> carros, Morador morador) {
        carros.forEach(carro -> {
            insert(carro, morador);
        });

    }

    public void updateList(List<Carro> carros) {
        carros.forEach(carro -> {
           update(carro, carro.getId());
        });

    }

    public Carro update(Carro carro, Long id) {

        System.out.println(id);
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Carro> optional = getCarroById(id);

        if(optional.isPresent()) {
            Carro carroDb = optional.get();

            carroDb.setModelo(carro.getModelo());
            carroDb.setCor(carro.getCor());
            carroDb.setPlaca(carro.getPlaca());

            rep.save(carroDb);

            return carroDb;

        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }

    }

    public Optional<Carro> getCarroById(Long id) {
        return rep.findById(id);
    }

    public Optional<Carro> getCarroByIdAndMoradorId(Long id, Long morador_id) {
        return rep.findByIdAndMoradorId(id, morador_id);
    }

}
