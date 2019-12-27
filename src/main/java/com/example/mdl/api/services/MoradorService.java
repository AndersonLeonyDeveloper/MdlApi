package com.example.mdl.api.services;

import com.example.mdl.api.entities.*;
import com.example.mdl.api.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository rep;

    @Autowired
    private CarroService carroService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private BlocoAptoService blocoAptoService;

    public MoradorDTO getMoradorById(Long id) {
        Assert.notNull(id, "Não foi possível buscar o registro.");

        Optional<Morador> optional = rep.findById(id);
        List<Carro> carros = carroService.getListOfCarsByMoradorId(id);
        List<Telefone> telefones = telefoneService.getListOfTelefonesByMoradorId(id);

        MoradorDTO moradorDTO = new MoradorDTO();

        if(optional.isPresent()){
            moradorDTO.setId(optional.get().getId());
            moradorDTO.setNome(optional.get().getNome());
            moradorDTO.setInadimplente(optional.get().getInadimplente());
            moradorDTO.setBlocoApto(optional.get().getBlocoApto());
            moradorDTO.setCarros(carroService.getListOfCarsByMoradorId(id));
            moradorDTO.setTelefones(telefoneService.getListOfTelefonesByMoradorId(id));

        } else {
            throw new RuntimeException("Não foi possível buscar o registro.");
        }

        return moradorDTO;
    }

    public Iterable<Morador> getMoradores() {
        return rep.findAll();
    }

    public Iterable<Morador> getMoradorBySituacao(Boolean situacao) {
        return rep.findByInadimplente(situacao);
    }

    public MoradorDTO insert(MoradorDTO moradorDTO) {
        Assert.isNull(moradorDTO.getId(), "Não foi possível atualizar o registro.");

        BlocoApto blocoAptoAdd = blocoAptoService.getBlocoApto(moradorDTO.getBlocoApto().getBloco(), moradorDTO.getBlocoApto().getApto());

        System.out.println(moradorDTO.getBlocoApto().toString());

        Morador moradorAdd = new Morador();
        moradorAdd.setNome(moradorDTO.getNome());
        moradorAdd.setInadimplente(moradorDTO.getInadimplente());
        moradorAdd.setBlocoApto(blocoAptoAdd);
        moradorAdd.setId(rep.save(moradorAdd).getId());

        if(!moradorDTO.getCarros().isEmpty()){
            carroService.insertList(moradorDTO.getCarros(), moradorAdd);
        }

        if(!moradorDTO.getTelefones().isEmpty()){
            telefoneService.insertList(moradorDTO.getTelefones(), moradorAdd);
        }

        return moradorDTO;
    }

    public Morador update(Morador morador, Long id) {
        System.out.println(id);
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        // Busca o morador no BD
        Optional<Morador> optional = rep.findById(id);

        if (optional.isPresent()) {
            Morador moradorDb = optional.get();
            // Copiar as propriedades que estão vindo do JSON
            moradorDb.setNome(morador.getNome());
            moradorDb.setBlocoApto(morador.getBlocoApto());
            moradorDb.setInadimplente(morador.getInadimplente());

            List<Carro> carrosDb = carroService.getListOfCarsByMoradorId(id);
//            if(!carrosDb.isEmpty()){
//                carroService.updateList(morador.getCarros());
//            }
//
//            List<Telefone> telefonesDb = telefoneService.getListOfTelefonesByMoradorId(id);
//            if(!telefonesDb.isEmpty()){
//                telefoneService.updateList(morador.getTelefones());
//            }

//            moradorDb.setCarros(morador.getCarros());
//            moradorDb.setTelefones(morador.getTelefones());

            System.out.println("Morador id = " + moradorDb.getId());

            //Atualiza o morador;
            rep.save(moradorDb);

            return moradorDb;

        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }

//        getMoradorById(id).map(db -> {
//            // Copiar as propriedades que estão vindo do JSON
//            db.setNome(morador.getNome());
//            db.setBlocoApto(morador.getBlocoApto());
//            db.setCarros(morador.getCarros());
//            db.setInadimplente(morador.getInadimplente());
//            db.setTelefones(morador.getTelefones());
//            System.out.println("Morador id = "+db.getId());
//            // Atualiza o carro
//            rep.save(db);
//            return db;
//        }).orElseThrow( () -> new RuntimeException("Não foi possível atualizar o registro."));
    }

    public void delete(Long id) {
        Optional<Morador> morador = rep.findById(id);
        if (morador.isPresent()) {
            rep.deleteById(id);
        } else {
            throw new RuntimeException("Não foi possível deletar o registro.");
        }
    }
}
