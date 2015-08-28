package br.com.munieri.desafio.explorando.marte.domain.sonda.command;

import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class VirarEsquerda implements Comando {

    SondaEntity sonda;

    public VirarEsquerda(SondaEntity sonda) {
        this.sonda = sonda;
    }

    @Override
    public void executa() {
        sonda.viraEsquerda();
        sonda.atualizaDirecaoAtual();
    }
}
