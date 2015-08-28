package br.com.munieri.desafio.explorando.marte.domain.sonda.command;

import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class VirarDireita implements Comando {

    SondaEntity sonda;

    public VirarDireita(SondaEntity sonda) {
        this.sonda = sonda;
    }

    @Override
    public void executa() {
        sonda.viraDireita();
        sonda.atualizaDirecaoAtual();
    }
}
