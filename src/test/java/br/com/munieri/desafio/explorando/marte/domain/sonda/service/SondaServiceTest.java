package br.com.munieri.desafio.explorando.marte.domain.sonda.service;

import br.com.munieri.desafio.explorando.marte.domain.sonda.Sonda;
import br.com.munieri.desafio.explorando.marte.domain.sonda.SondaNotFound;
import br.com.munieri.desafio.explorando.marte.domain.sonda.builder.SondaBuilder;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaEntity;
import br.com.munieri.desafio.explorando.marte.infrastructure.repository.sonda.SondaRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class SondaServiceTest {

    @InjectMocks
    SondaServiceImpl service;

    @Mock
    SondaRepository repository;

    @BeforeMethod
    public void init(){
        service = new SondaServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

//    @Test(expectedExceptions = SondaNotFound.class)
//    public void deveria_lancar_excecao_quando_nao_encontar_sonda(){
//
//        when(repository.findOne(anyLong())).thenReturn(null);
//        service.find(1L);
//    }

    @Test
    public void deveria_persistir_nova_sonda(){

        Sonda sonda = SondaBuilder.newBuilder()
                .id(1L)
                .posicao("1 2 N")
                .comandos("LMLMLMM")
                .build();
        SondaEntity mock = new SondaEntity(1L, "1 2 N", "LMLMLMM");
        mock.posicaoAtual("1 3 N");

        when(repository.save((SondaEntity) any())).thenReturn(mock);
        sonda = service.create(sonda);
        assertEquals(sonda.id().longValue(), 1L);
    }
}