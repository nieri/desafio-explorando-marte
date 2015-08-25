package br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import javax.persistence.*;

@Entity
public class SondaEntity implements Sonda{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @Column
    private String inicio;

    @Column
    private String coordenada;

    @Column
    private String comandos;

    public SondaEntity() {
    }

    public SondaEntity(Long id, String nome, String inicio, String coordenada, String comando) {
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.coordenada = coordenada;
        this.comandos = comando;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String inicio() {
        return inicio;
    }

    @Override
    public String coordenadas() {
        return coordenada;
    }

    @Override
    public String comandos() {
        return comandos;
    }
}


