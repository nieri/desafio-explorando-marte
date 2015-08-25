package br.com.munieri.desafio.explorando.marte.domain.sonda.builder;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class SondaBuilder {

    public static SondaBuilderStep newBuilder() {
        return new Steps();
    }

    public interface SondaBuilderStep {

        SondaBuilderStep id(Long id);

        SondaBuilderNomeStep nome(String nome);

    }

    public interface SondaBuilderNomeStep {

        SondaBuilderInicioStep inicio(String Inicio);
    }

    public interface SondaBuilderInicioStep {

        SondaBuilderCoordenadasStep coordenadas(String coordenadas);
    }

    public interface SondaBuilderCoordenadasStep {

        SondaBuilderFinalStep comandos(String comandos);
    }

    public interface SondaBuilderFinalStep {

        Sonda build();
    }

    private static class Steps implements SondaBuilderStep, SondaBuilderNomeStep, SondaBuilderInicioStep, SondaBuilderCoordenadasStep, SondaBuilderFinalStep {

        private Long id;
        private String nome;
        private String inicio;
        private String coordenadas;
        private String comandos;

        @Override
        public SondaBuilderStep id(Long id) {
            this.id = id;
            return this;
        }

        @Override
        public SondaBuilderNomeStep nome(String nome) {
            this.nome = nome;
            return this;
        }

        @Override
        public SondaBuilderInicioStep inicio(String inicio) {
            this.inicio = inicio;
            return this;
        }

        @Override
        public SondaBuilderFinalStep comandos(String comandos) {
            this.comandos = comandos;
            return this;
        }

        @Override
        public Sonda build() {
            return new SondaEntity(id, nome, inicio, coordenadas, comandos);
        }

        @Override
        public SondaBuilderCoordenadasStep coordenadas(String coordenadas) {
            this.coordenadas = coordenadas;
            return this;
        }
    }
}
