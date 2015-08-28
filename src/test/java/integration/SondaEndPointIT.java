package integration;

import br.com.munieri.desafio.explorando.marte.view.endpoint.plato.PlatoDTO;
import br.com.munieri.desafio.explorando.marte.view.endpoint.sonda.SondaDTO;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import integration.shared.IntegrationServer;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class SondaEndPointIT extends IntegrationServer {

    private void criaSonda() {

        PlatoDTO platoDTO = new PlatoDTO();
        platoDTO.setLimiteSuperiorEixoX(5);
        platoDTO.setLimiteSuperiorEixoy(5);

        List<SondaDTO> sondasDTO = new ArrayList<>();

        SondaDTO sonda1 = new SondaDTO();
        sonda1.setId(1L);
        sonda1.setPosicao("1 2 N");
        sonda1.setComandos("LMLMLMM");
        sondasDTO.add(sonda1);

        SondaDTO sonda2 = new SondaDTO();
        sonda2.setId(1L);
        sonda2.setPosicao("1 2 N");
        sonda2.setComandos("LMLMLMM");
        sondasDTO.add(sonda2);

        SondaDTO sonda3 = new SondaDTO();
        sonda3.setId(1L);
        sonda3.setPosicao("1 2 N");
        sonda3.setComandos("LMLMLMM");
        sondasDTO.add(sonda3);

        platoDTO.setSondas(sondasDTO);


        JsonPath path =  given()
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(platoDTO)
                .expect()
                .statusCode(HttpStatus.SC_CREATED)
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .andReturn()
                .jsonPath();

        List<SondaDTO> resposta = path.getList("sonda", SondaDTO.class);

        assertEquals(platoDTO.getSondas().get(0).getId(), resposta.get(0).getId());

    }

    private void criaSondaSemComandos() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"id\": \"1\",\"posicao\": \"1 2 N\"}")
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("code", equalTo("comandos"))
                .and()
                .body("message", equalTo("Informe o campo comandos"));
    }

    private void criaSondaSemPosicao() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"id\": \"1\", \"comandos\": \"LMLMLMLMM\"}")
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("code", equalTo("posicao"))
                .and()
                .body("message", equalTo("Informe o campo posicao"));
    }

    private void criaSondaComContentTypeErrado() {
        given()
                .contentType(ContentType.HTML)
                .body("{ \"id\": \"1\",\"posicao\": \"1 2 N\", \"comandos\": \"LMLMLMLMM\"}")
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    private void criaSondaSemInformacoes() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void deveria_criar_sonda() {
        criaSonda();
    }
//
//    @Test
//    public void deveria_lancar_excecao_ao_criar_sonda_sem_comandos() {
//        criaSondaSemComandos();
//    }
//
//    @Test
//    public void deveria_lancar_excecao_ao_criar_sonda_sem_posicao() {
//        criaSondaSemPosicao();
//    }
//    @Test
//    public void deveria_lancar_excecao_quando_nao_informar_media_type_correto() {
//        criaSondaComContentTypeErrado();
//    }
//
//    @Test
//    public void deveria_lancar_excecao_quando_nao_informar_parametros_corretos_correto() {
//        criaSondaSemInformacoes();
//    }
}