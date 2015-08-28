package br.com.munieri.desafio.explorando.marte.domain.sonda.command;

import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class Mover implements Comando {

    SondaEntity sonda;

    public Mover(SondaEntity sonda) {
        this.sonda = sonda;
    }

    @Override
    public void executa() {
        sonda.mover();
        sonda.atualizaDirecaoAtual();
    }
}
