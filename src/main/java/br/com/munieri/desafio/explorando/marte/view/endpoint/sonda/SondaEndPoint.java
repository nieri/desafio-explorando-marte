package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.builder.SondaBuilder;
import br.com.munieri.desafio.explorando.marte.domain.sonda.service.SondaService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SondaEndPoint {

    @Autowired
    private SondaService sondaService;

    @RequestMapping(value = "/sonda", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SondaDTO dto) {

        check(dto);

        Sonda sonda = criaSonda(dto);

        return parseToResponse(sonda, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sonda{id}", method = RequestMethod.GET)
    public HttpStatus get(@PathVariable Long id) {

        return HttpStatus.OK;
    }

    private Sonda criaSonda(SondaDTO dto) {

        Sonda sonda = SondaBuilder.newBuilder()
                .id(dto.getId())
                .nome(dto.getNome())
                .inicio(dto.getInicio())
                .coordenadas(dto.getCoordenadas())
                .comandos(dto.getComandos())
                .build();

        return sondaService.create(sonda);
    }

    private ResponseEntity parseToResponse(Sonda sonda, HttpStatus status) {
        return new ResponseEntity<>(new SondaDTO(sonda), status);
    }

    private void check(SondaDTO dto) {
        if(StringUtil.isBlank(dto.getInicio())) {
            throw new IllegalArgumentException("inicio_obrigatorio");
        }
    }
}
