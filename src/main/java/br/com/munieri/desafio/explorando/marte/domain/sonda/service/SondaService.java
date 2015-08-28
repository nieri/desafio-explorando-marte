package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;

import java.util.List;

public interface SondaService {

    List<Sonda> create(List<Sonda> sondas);

    Sonda find(Long id) throws SondaNotFound;
}
