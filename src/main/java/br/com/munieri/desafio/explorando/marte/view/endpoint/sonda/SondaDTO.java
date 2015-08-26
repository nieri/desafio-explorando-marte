package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;

public class SondaDTO {

    private Long id;
    private String posicao;
    private String comandos;
    private String posicaoAtual;

    public SondaDTO() {
    }

    public SondaDTO(Sonda sonda) {
        this.id = sonda.id();
        this.posicao = sonda.posicao();
        this.comandos = sonda.comandos();
        this.posicaoAtual = sonda.posicaoAtual();
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

    public String getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(String posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }
}
