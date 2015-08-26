package br.com.munieri.desafio.explorando.marte.domain.sonda.builder;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class SondaBuilder {

    public static SondaBuilderStep newBuilder() {
        return new Steps();
    }

    public interface SondaBuilderStep {

        SondaBuilderStep id(Long id);

        SondaBuilderPosicaoStep posicao(String Inicio);

    }

    public interface SondaBuilderPosicaoStep {
        SondaBuilderFinalStep comandos(String comandos);
    }

    public interface SondaBuilderFinalStep {
        Sonda build();
    }

    private static class Steps implements SondaBuilderStep, SondaBuilderPosicaoStep, SondaBuilderFinalStep {

        private Long id;
        private String posicao;
        private String comandos;

        @Override
        public SondaBuilderStep id(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public SondaBuilderPosicaoStep posicao(String inicio) {
            this.posicao = inicio;
            return this;
        }

        @Override
        public SondaBuilderFinalStep comandos(String comandos) {
            this.comandos = comandos;
            return this;
        }

        @Override
        public Sonda build() {
            return new SondaEntity(id, posicao, comandos);
        }

    }
}
