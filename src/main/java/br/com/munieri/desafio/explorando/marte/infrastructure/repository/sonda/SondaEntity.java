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
}


