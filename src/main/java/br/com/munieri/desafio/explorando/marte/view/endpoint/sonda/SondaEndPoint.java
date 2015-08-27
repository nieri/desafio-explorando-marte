package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.builder.SondaBuilder;
import br.com.munieri.desafio.explorando.marte.domain.sonda.service.SondaService;
import br.com.munieri.desafio.explorando.marte.view.endpoint.ErrorDTO;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class SondaEndPoint {

    @Autowired
    private SondaService sondaService;

    @RequestMapping(value = "/sonda", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SondaDTO dto) {

        check(dto);

        Sonda sonda = criaSonda(dto);

        return parseToResponse(sonda, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sonda/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {

        return parseToResponse(sondaService.find(id), HttpStatus.CREATED);
    }

    private Sonda criaSonda(SondaDTO dto) {

        Sonda sonda = SondaBuilder.newBuilder()
                .id(dto.getId())
                .posicao(dto.getPosicao())
                .comandos(dto.getComandos())
                .build();

        return sondaService.create(sonda);
    }

    private ResponseEntity parseToResponse(Sonda sonda, HttpStatus status) {
        return new ResponseEntity<>(new SondaDTO(sonda), status);
    }

    private void check(SondaDTO dto) {
        checkCampo(dto.getPosicao(), "posicao");
        checkCampo(dto.getComandos(), "comandos");
    }

    private void checkCampo(String campo, String codigo) {
        if (StringUtil.isBlank(campo)) {
            throw new IllegalArgumentException(codigo);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO posicaoInvalidaHandler(IllegalArgumentException ex, HttpServletResponse response) {
        return new ErrorDTO(ex.getMessage(), "Informe o compo " + ex.getMessage());
    }

}
