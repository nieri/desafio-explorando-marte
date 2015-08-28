package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;
import br.com.munieri.desafio.explorando.marte.domain.sonda.command.Controlador;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SondaServiceImpl implements SondaService{

    @Autowired
    SondaRepository repository;

    @Override
    public Sonda create(Sonda sonda) {

        Controlador controlador = new Controlador((SondaEntity)sonda);
        controlador.processa(sonda.comandos());

        return persist(sonda);
    }

    @Override
    public Sonda update(Sonda sonda) throws SondaNotFound {
        return null;
    }

    @Override
    public Sonda find(Long id) throws SondaNotFound {
        return repository.findOne(id);
    }

    private Sonda persist(Sonda sonda) {
        return repository.save((SondaEntity) sonda);
    }
}
