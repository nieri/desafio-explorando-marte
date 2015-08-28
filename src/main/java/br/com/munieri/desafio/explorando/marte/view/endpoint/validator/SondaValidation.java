package br.com.munieri.desafio.explorando.marte.view.endpoint.validator;

import br.com.munieri.desafio.explorando.marte.view.endpoint.sonda.SondaDTO;
import org.eclipse.jetty.util.StringUtil;

import java.util.List;

public class SondaValidation {

    public static void check(List<SondaDTO> list) {

        for(SondaDTO dto : list) {
            checkCampo(dto.getPosicao(), "posicao");
            checkCampo(dto.getComandos(), "comandos");
        }
    }

    private static void checkCampo(String campo, String codigo) {
        if (StringUtil.isBlank(campo)) {
            throw new IllegalArgumentException(codigo);
        }
    }
}
