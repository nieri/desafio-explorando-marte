package br.com.munieri.desafio.explorando.marte.domain.sonda.command;

import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;

public class Controlador {

    ControleRemoto controleRemoto = new ControleRemoto();

    Comando virarDireita;
    Comando virarEsquerda ;
    Comando mover;

    public Controlador(SondaEntity sonda) {
        virarDireita = new VirarDireita(sonda);
        virarEsquerda = new VirarEsquerda(sonda);
        mover = new Mover(sonda);
    }

    public void processa(String comandos) {

        for (int i = 0; i < comandos.length(); i++) {
            processa(comandos.charAt(i));
        }
    }

    private void processa(Character comando) {
        if (comando.equals('L')) {
            controleRemoto.setComando(virarDireita);
            controleRemoto.start();
        } else if (comando.equals('R')) {
            controleRemoto.setComando(virarEsquerda);
            controleRemoto.start();
        } else if (comando.equals('M')) {
            controleRemoto.setComando(mover);
            controleRemoto.start();
        } else {
            throw new IllegalArgumentException("Comando Invalido!");
        }
    }

    public static void main(String... arg) {

        SondaEntity sonda = new SondaEntity(1L, "1 2 N", "LMLMLMLMM");

        Controlador controlador = new Controlador(sonda);
        controlador.processa("LMLMLMLMM");

        System.out.println(sonda.toString());
    }
}
