package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;
import br.com.munieri.desafio.explorando.marte.domain.sonda.command.Controlador;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SondaServiceImpl implements SondaService{

    @Autowired
    SondaRepository repository;

    @Override
    public List<Sonda> create(List<Sonda> sondas) {

        List<Sonda> sondasGravadas = new ArrayList<>();

        for (Sonda sonda :sondas) {
            Controlador controlador = new Controlador((SondaEntity) sonda);
            controlador.processa(sonda.comandos());
            sondasGravadas.add(persist(sonda));
        }
        return sondasGravadas;
    }

    @Override
    public Sonda find(Long id) throws SondaNotFound {
        return repository.findOne(id);
    }

    private Sonda persist(Sonda sonda) {
        return repository.save((SondaEntity) sonda);
    }
}
