package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Coordenada;
import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SondaServiceImpl implements SondaService{

    private Integer x;
    private Integer y;
    private Integer direcao;

    @Autowired
    SondaRepository repository;

    @Override
    public Sonda create(Sonda sonda) {

        return persist(processa(sonda));
    }

    @Override
    public Sonda update(Sonda sonda) throws SondaNotFound {
        return null;
    }

    @Override
    public Sonda find(Long id) throws SondaNotFound {
        return repository.findOne(id);
    }

    private Sonda persist(Sonda sonda) {
        return repository.save((SondaEntity) sonda);
    }

    public void setDirecao(String posicao) {
        splitCaracteres(posicao);
    }

    private void splitCaracteres(String posicao) {
        String palavras[] = posicao.split(" ");

        this.x = Integer.parseInt(palavras[0]);
        this.y = Integer.parseInt(palavras[1]);
        this.direcao = getDirecao(palavras[2]);
    }

    public String posicaoAtualFormatada() {
        return x + " " + y + " " + Coordenada.direcao(direcao);
    }

    private Integer getDirecao(String direcao) {
        return Coordenada.direcaoValue(direcao);
    }

    private Sonda processa(Sonda sonda) {

        this.setDirecao(sonda.posicao());

        for (int i = 0; i < sonda.comandos().length(); i++) {
            processa(sonda.comandos().charAt(i));
        }

        sonda.posicaoAtual(posicaoAtualFormatada());

        return sonda;
    }
    private void processa(Character comando) {
        if (comando.equals('L')) {
            viraDireita();
        } else if (comando.equals('R')) {
            viraEsquerda();
        } else if (comando.equals('M')) {
            mover();
        } else {
            throw new IllegalArgumentException("Comando Invalido!");
        }
    }

    private void mover() {
        if (direcao == Coordenada.NORTH.val()) {
            this.y++;
        } else if (direcao == Coordenada.EAST.val()) {
            this.x++;
        } else if (direcao == Coordenada.SOUTH.val()) {
            this.y--;
        } else if (direcao == Coordenada.WEST.val()) {
            this.x--;
        }
    }
    private void viraDireita() {
        direcao = (direcao - 1) < Coordenada.NORTH.val() ? Coordenada.WEST.val() : direcao - 1;
    }
    private void viraEsquerda() {
        direcao = (direcao + 1) > Coordenada.WEST.val() ? Coordenada.NORTH.val() : direcao + 1;
    }
}
