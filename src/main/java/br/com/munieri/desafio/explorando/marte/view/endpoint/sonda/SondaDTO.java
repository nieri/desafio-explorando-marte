package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;

public class SondaDTO {

    private Long id;
    private String posicao;
    private String comandos;

    public SondaDTO() {
    }

    public SondaDTO(Sonda sonda) {
        this.id = sonda.id();
        this.posicao = sonda.posicao();
        this.comandos = sonda.comandos();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getComandos() {
        return comandos;
    }

    public void setComandos(String comandos) {
        this.comandos = comandos;
    }
}
