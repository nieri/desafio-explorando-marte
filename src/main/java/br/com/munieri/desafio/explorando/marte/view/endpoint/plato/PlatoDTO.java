package br.com.munieri.desafio.explorando.marte.view.endpoint.plato;

import br.com.munieri.desafio.explorando.marte.view.endpoint.sonda.SondaDTO;

import java.util.List;

public class PlatoDTO {

    private Integer limiteSuperiorEixoX;
    private Integer limiteSuperiorEixoy;
    private Integer limiteInferiorEixoX = 0;
    private Integer limiteInferiorEixoy = 0;
    private List<SondaDTO> sondas;

    public PlatoDTO() {
    }

    public PlatoDTO(Integer limiteSuperiorEixoX, Integer limiteSuperiorEixoy) {
        this.limiteSuperiorEixoX = limiteSuperiorEixoX;
        this.limiteSuperiorEixoy = limiteSuperiorEixoy;
    }

    public Integer getLimiteSuperiorEixoy() {
        return limiteSuperiorEixoy;
    }

    public void setLimiteSuperiorEixoy(Integer limiteSuperiorEixoy) {
        this.limiteSuperiorEixoy = limiteSuperiorEixoy;
    }

    public Integer getLimiteSuperiorEixoX() {
        return limiteSuperiorEixoX;
    }

    public void setLimiteSuperiorEixoX(Integer limiteSuperiorEixoX) {
        this.limiteSuperiorEixoX = limiteSuperiorEixoX;
    }

    public List<SondaDTO> getSondas() {
        return sondas;
    }

    public void setSondas(List<SondaDTO> sondas) {
        this.sondas = sondas;
    }
}
