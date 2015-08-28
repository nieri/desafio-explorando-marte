package br.com.munieri.desafio.explorando.marte.domain.sonda.command;

public class ControleRemoto {

    Comando comando;

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public void start(){
        comando.executa();
    }
}
