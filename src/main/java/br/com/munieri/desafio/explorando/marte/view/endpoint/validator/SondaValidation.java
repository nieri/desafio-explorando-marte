package br.com.munieri.desafio.explorando.marte.view.endpoint.validator;

import br.com.munieri.desafio.explorando.marte.view.endpoint.sonda.SondaDTO;
import org.eclipse.jetty.util.StringUtil;

public class SondaValidation {

    public static void check(SondaDTO dto) {
        checkCampo(dto.getPosicao(), "posicao");
        checkCampo(dto.getComandos(), "comandos");
    }

    private static void checkCampo(String campo, String codigo) {
        if (StringUtil.isBlank(campo)) {
            throw new IllegalArgumentException(codigo);
        }
    }
}
