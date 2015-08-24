package br.com.munieri.desafio.explorando.marte.boot;

import br.com.munieri.desafio.explorando.marte.boot.server.ApplicationServer;
import br.com.munieri.desafio.explorando.marte.boot.server.WebServer;

public class Main {

    public static void main(String[] args) throws Exception {
        WebServer server = new ApplicationServer();
        server.start();
    }
}
