package br.com.munieri.desafio.explorando.marte.view.endpoint.sonda;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.builder.SondaBuilder;
import br.com.munieri.desafio.explorando.marte.domain.sonda.service.SondaService;
import br.com.munieri.desafio.explorando.marte.view.endpoint.ErrorDTO;
import br.com.munieri.desafio.explorando.marte.view.endpoint.plato.PlatoDTO;
import br.com.munieri.desafio.explorando.marte.view.endpoint.validator.SondaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class SondaEndPoint {

    @Autowired
    private SondaService sondaService;

    @RequestMapping(value = "/sonda", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody PlatoDTO dto) {

        SondaValidation.check(dto.getSondas());

        List<Sonda> sondas = criaSonda(dto);

        return parseToResponse(sondas, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sonda/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {

        return parseToResponse(sondaService.find(id), HttpStatus.CREATED);
    }

    private List<Sonda> criaSonda(PlatoDTO dto) {

        List<Sonda> sondas = new ArrayList<>();
        for (SondaDTO sonda: dto.getSondas()){
            sondas.add(SondaBuilder.newBuilder()
                    .id(sonda.getId())
                    .posicao(sonda.getPosicao())
                    .comandos(sonda.getComandos())
                    .build());
        }

        return sondaService.create(sondas);
    }

    private ResponseEntity parseToResponse(Sonda sonda, HttpStatus status) {
        return new ResponseEntity<>(new SondaDTO(sonda), status);
    }

    private ResponseEntity parseToResponse(List<Sonda> sondas, HttpStatus status) {

        List<SondaDTO> listaSondasDTO = new ArrayList<>();
        for (Sonda sonda : sondas){
            listaSondasDTO.add(new SondaDTO(sonda));
        }

        return new ResponseEntity<>(listaSondasDTO, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO posicaoInvalidaHandler(IllegalArgumentException ex, HttpServletResponse response) {
        return new ErrorDTO(ex.getMessage(), "Informe o campo " + ex.getMessage());
    }
}
