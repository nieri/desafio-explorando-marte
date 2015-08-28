package br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Coordenada;
import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;

import javax.persistence.*;

@Entity
public class SondaEntity implements Sonda {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String posicao;

    @Column
    private String comandos;

    @Column
    private String posicaoAtual;

    private Integer eixoX;
    private Integer eixoY;
    private Integer direcao;

    public SondaEntity() {
    }

    public SondaEntity(Long id, String posicao, String comandos) {
        this.id = id;
        this.posicao = posicao;
        this.comandos = comandos;
        splitCaracteres(posicao);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String posicao() {
        return posicao;
    }

    @Override
    public String comandos() {
        return comandos;
    }

    @Override
    public String posicaoAtual() {
        return posicaoAtual;
    }

    public void viraDireita() {
        direcao = (direcao - 1) < Coordenada.NORTH.val() ? Coordenada.WEST.val() : direcao - 1;
    }

    public void viraEsquerda() {
        direcao = (direcao + 1) > Coordenada.WEST.val() ? Coordenada.NORTH.val() : direcao + 1;
    }

    public void mover() {
        if (direcao == Coordenada.NORTH.val()) {
            this.eixoY++;
        } else if (direcao == Coordenada.EAST.val()) {
            this.eixoX++;
        } else if (direcao == Coordenada.SOUTH.val()) {
            this.eixoY--;
        } else if (direcao == Coordenada.WEST.val()) {
            this.eixoX--;
        }
    }

    private void splitCaracteres(String posicao) {

        String palavras[] = posicao.split(" ");

        this.eixoX = Integer.parseInt(palavras[0]);
        this.eixoY = Integer.parseInt(palavras[1]);
        this.direcao = getDirecao(palavras[2]);
    }

    private Integer getDirecao(String direcao) {
        return Coordenada.direcaoIntValue(direcao);
    }

    public void atualizaDirecaoAtual() {
        this.posicaoAtual = eixoX + " " + eixoY + " " + Coordenada.direcaoStringValue(direcao);
    }
}


