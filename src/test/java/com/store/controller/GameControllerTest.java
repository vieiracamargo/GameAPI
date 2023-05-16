package com.store.controller;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@TestHTTPEndpoint(GameController.class)
class GameControllerTest {
    @Test
    void getGameById() {
        given()
                .pathParam("id", 1)
                .when().get("{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1),
                        "title", equalTo("Cyberpunk 2077"),
                        "description", equalTo("Em 2077, a América está arruinada. Megacorporações controlam a vida em todos os aspectos, do topo de seus arranha-céus até as atividades ilícitas que acontecem nas ruas. O mundo entre esses dois extremos é onde a decadência, sexo e a cultura popular se misturam com crimes violentos, pobreza extrema e a promessa inatingível do Sonho Americano.Em um mundo em que você não tem futuro, o que importa é ter controle sob o que você é. Para sobreviver e proteger sua independência, você modifica seu corpo com itens cibernéticos e faça trabalhos que jamais ousaria. Escolha viver livremente, independente de sistemas ou controles – você obedece às suas regras. Em Cyberpunk você joga como V – um assassino(a) de alguel em ascensão – e você acabou de conseguir seu primeiro contrato sério. Em um mundo de guerreiros aprimorados cibernéticamente, gênios da tecnologia e lifehackers corporativos, você pode dar o primeiro passo para se tornar uma lenda urbana."),
                        "releaseDate", equalTo("2020-12-10"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=UnA7tepsc7s")
                );
    }

    @Test
    void getAllGames() {
        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0));
    }

    @Test
    void createGame() {
        Map<String,String> game = new HashMap<>();
        game.put("title", "The Last of Us Part I");
        game.put("description", "Em uma civilização devastada, em que infectados e sobreviventes veteranos estão à solta, Joel, um protagonista abatido, é contratado para tirar uma garota de 14 anos, Ellie, de uma zona de quarentena militar.");
        game.put("releaseDate", "2022-09-02");
        game.put("trailerUrl", "https://www.youtube.com/watch?v=CxVyuE2Nn_w");

        given()
                .contentType("application/json")
                .body(game)
                .when().post()
                .then()
                .statusCode(201)
                .body(
                        "title", equalTo("The Last of Us Part I"),
                        "description", equalTo("Em uma civilização devastada, em que infectados e sobreviventes veteranos estão à solta, Joel, um protagonista abatido, é contratado para tirar uma garota de 14 anos, Ellie, de uma zona de quarentena militar."),
                        "releaseDate", equalTo("2022-09-02"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=CxVyuE2Nn_w")
                );
    }

    @Test
    void updateGame() {
        Map<String,String> game = new HashMap<>();
        game.put("title", "Elden Ring");
        game.put("description", "Em Elden Ring, o jogador incorpora um Maculado, guerreiro guiado pela força da Graça para portar o poder do Anel Prístino e torna-se um Lorde Prístino. A trama tem como cenário as Terras Intermédias, um local governado pela Rainha Marika.");
        game.put("releaseDate", "2022-02-25");
        game.put("trailerUrl", "https://www.youtube.com/watch?v=OT8if6DXOFQ");

        given()
                .contentType("application/json")
                .pathParam("id",1)
                .body(game)
                .when().put("{id}")
                .then()
                .statusCode(200)
                .body(
                        "title", equalTo("Elden Ring"),
                        "description", equalTo("Em Elden Ring, o jogador incorpora um Maculado, guerreiro guiado pela força da Graça para portar o poder do Anel Prístino e torna-se um Lorde Prístino. A trama tem como cenário as Terras Intermédias, um local governado pela Rainha Marika."),
                        "releaseDate", equalTo("2022-02-25"),
                        "trailerUrl", equalTo("https://www.youtube.com/watch?v=OT8if6DXOFQ")
                );
    }

    @Test
    void deleGameById() {
        given()
                .pathParam("id", 1)
                .when().delete("{id}")
                .then()
                .statusCode(204);
    }



}