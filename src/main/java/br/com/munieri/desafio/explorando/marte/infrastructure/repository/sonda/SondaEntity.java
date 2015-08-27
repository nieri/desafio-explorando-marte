package br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import javax.persistence.*;

@Entity
public class SondaEntity implements Sonda{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String posicao;

    @Column
    private String comandos;

    @Column
    private String posicaoAtual;

    public SondaEntity() {
    }

    public SondaEntity(Long id, String posicao, String comandos) {
        this.id = id;
        this.posicao = posicao;
        this.comandos = comandos;
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

    @Override
    public void posicaoAtual(String posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }
}


