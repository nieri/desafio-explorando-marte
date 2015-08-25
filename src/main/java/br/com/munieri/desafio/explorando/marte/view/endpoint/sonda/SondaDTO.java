package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;

/**
 * Created by munieri on 24/08/15.
 */
public class SondaDTO {

    private Long id;
    private String nome;
    private String inicio;
    private String coordenadas;
    private String comandos;

    public SondaDTO() {
    }

    public SondaDTO(Sonda sonda) {
        this.id = sonda.id();
        this.nome = sonda.nome();
        this.inicio = sonda.inicio();
        this.coordenadas = sonda.coordenadas();
        this.comandos = sonda.comandos();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {

        this.coordenadas = coordenadas;
    }

    public String getComandos() {
        return comandos;
    }

    public void setComandos(String comandos) {
        this.comandos = comandos;
    }
}
