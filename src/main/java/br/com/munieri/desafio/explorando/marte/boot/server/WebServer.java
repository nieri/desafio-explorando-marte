package br.com.munieri.desafio.explorando.marte.boot.server;

/**
 * Created by munieri on 23/08/15.
 */
public interface WebServer {

    /**
     * Inicializa o servidor da aplicacao.<br />
     * A porta eh selecionada randomicamente
     * @throws Exception
     */
    void start() throws Exception;


    /**
     * Inicializa o servidor da aplicacao na porta especifica.
     * @param port numero da porta especifica em que o servidor deve rodar
     * @throws Exception
     */
    void start(int port) throws Exception;


    /**
     * Para o servidor da aplicacao
     * @throws Exception
     */
    void stop() throws Exception;

    /**
     * Retorna a porta na qual o servidor fez o bind
     * @return
     */
    int portNUmber();
}
