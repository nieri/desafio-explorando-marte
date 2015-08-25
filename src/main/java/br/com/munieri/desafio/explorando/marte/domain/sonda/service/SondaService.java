package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;

public interface SondaService {

    Sonda create(Sonda sonda);

    Sonda update(Sonda sonda) throws SondaNotFound;
}
