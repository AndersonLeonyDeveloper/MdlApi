package com.example.mdl.api.services;

import com.example.mdl.api.dto.CarroDTO;
import com.example.mdl.api.dto.MoradorDTO;
import com.example.mdl.api.dto.TelefoneDTO;
import com.example.mdl.api.entities.BlocoApto;
import com.example.mdl.api.entities.Morador;
import com.example.mdl.api.exception.ObjectNotFoundException;
import com.example.mdl.api.repositories.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Morador> moradorDTO = rep.findById(id);
        List<CarroDTO> carrosDTO = carroService.getListOfCarrosByMoradorId(id);
        List<TelefoneDTO> telefonesDTO = telefoneService.getListOfTelefonesByMoradorId(id);

        return moradorDTO.map(m -> new MoradorDTO(m, carrosDTO, telefonesDTO)).orElseThrow(() -> new ObjectNotFoundException("Morador não encontrado."));
    }

    public List<MoradorDTO> getMoradores() {

        return rep.findAll().stream().map(morador ->
                new MoradorDTO(
                        morador,
                        carroService.getListOfCarrosByMoradorId(morador.getId()),
                        telefoneService.getListOfTelefonesByMoradorId(morador.getId())
                )
        ).collect(Collectors.toList());

    }

    public Iterable<Morador> getMoradorBySituacao(Boolean situacao) {
        return rep.findByInadimplente(situacao);
    }

    public MoradorDTO insert(MoradorDTO moradorDTO) {
        Assert.isNull(moradorDTO.getId(), "Não foi possível adicionar o registro (id deve ser nulo nulo).");

        BlocoApto blocoAptoAdd = blocoAptoService.getBlocoApto(moradorDTO.getBlocoApto().getBloco(), moradorDTO.getBlocoApto().getApto());

        Morador moradorAdd = new Morador();
        moradorAdd.setNome(moradorDTO.getNome());
        moradorAdd.setInadimplente(moradorDTO.getInadimplente());
        moradorAdd.setBlocoApto(blocoAptoAdd);
        moradorAdd.setId(rep.save(moradorAdd).getId());
        moradorDTO.setId(moradorAdd.getId());
        carroService.insertOrUpdateList(moradorDTO.getCarros(), moradorAdd);
        telefoneService.insertOrUpdateList(moradorDTO.getTelefones(), moradorAdd);

        return moradorDTO;
    }

    public MoradorDTO update(MoradorDTO moradorDTO, Long moradorId) {
        System.out.println(moradorId);
        Assert.notNull(moradorId, "Não foi possível atualizar o registro (id do morador não pode ser nulo).");

        // Busca o morador no BD
        Optional<Morador> optional = rep.findById(moradorId);

        if (optional.isPresent()) {
            Morador moradorDb = optional.get();
            // Copiar as propriedades que estão vindo do JSON
            moradorDb.setNome(moradorDTO.getNome());
            moradorDb.setBlocoApto(moradorDTO.getBlocoApto());
            moradorDb.setInadimplente(moradorDTO.getInadimplente());
            carroService.insertOrUpdateList(moradorDTO.getCarros(), moradorDb);
            telefoneService.insertOrUpdateList(moradorDTO.getTelefones(), moradorDb);

            //Atualiza o morador;
            rep.save(moradorDb);

            return moradorDTO;

        } else {
            return null;
        }

    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
