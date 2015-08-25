package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SondaServiceImpl implements SondaService{

    @Autowired
    SondaRepository repository;

    @Override
    public Sonda create(Sonda sonda) {
        return null;
    }

    @Override
    public Sonda update(Sonda sonda) throws SondaNotFound {
        return null;
    }

    private Sonda persist(Sonda sonda) {
        return repository.save((SondaEntity) sonda);
    }
}
