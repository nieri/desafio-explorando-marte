package integration;

import com.jayway.restassured.http.ContentType;
import integration.shared.IntegrationServer;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class SondaEndPointIT extends IntegrationServer {

    private void criaSonda() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"id\": \"1\",\"posicao\": \"1 2 N\", \"comandos\": \"LMLMLMLMM\"}")
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/sonda")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("posicao", equalTo("1 2 N"))
                .and()
                .body("comandos", equalTo("LMLMLMLMM"))
                .and()
                .body("posicaoAtual", equalTo("1 3 N"));
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
                .body("message", equalTo("Informe o compo comandos"));
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
                .body("message", equalTo("Informe o compo posicao"));
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

    @Test
    public void deveria_lancar_excecao_ao_criar_sonda_sem_comandos() {
        criaSondaSemComandos();
    }

    @Test
    public void deveria_lancar_excecao_ao_criar_sonda_sem_posicao() {
        criaSondaSemPosicao();
    }
    @Test
    public void deveria_lancar_excecao_quando_nao_informar_media_type_correto() {
        criaSondaComContentTypeErrado();
    }

    @Test
    public void deveria_lancar_excecao_quando_nao_informar_parametros_corretos_correto() {
        criaSondaSemInformacoes();
    }
}