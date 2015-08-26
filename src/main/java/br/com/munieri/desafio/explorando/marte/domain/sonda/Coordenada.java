package br.com.munieri.desafio.explorando.marte.domain.sonda;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Coordenada {

    NORTH(1, "N"), EAST(2, "E"), SOUTH(3, "S"), WEST(4, "W");

    private Integer direçao;
    private String descricao;

    Coordenada(Integer direçao, String descricao) {
        this.direçao = direçao;
        this.descricao = descricao;
    }

    public Integer val() {
        return direçao;
    }

    public static Coordenada direcao(int i) {

        List<Coordenada> direcoes = new ArrayList<Coordenada>(EnumSet.allOf(Coordenada.class));
        return direcoes.get(i);
    }

    public static Integer direcaoValue(String value) {

        List<Coordenada> direcoes = new ArrayList<Coordenada>(EnumSet.allOf(Coordenada.class));
        for (Coordenada direcao : direcoes){
            if(direcao.descricao.equals(value)){
                return direcao.direçao;
            }
        }
        throw new IllegalArgumentException("Direcao Invalida!");
    }

    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return NORTH.descricao;
            case EAST:
                return EAST.descricao;
            case SOUTH:
                return SOUTH.descricao;
            case WEST:
                return WEST.descricao;
            default:
                return null;
        }
    }
}
