package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.exception.GameNotFoundException;
import com.store.repository.GamesRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@Tag("unit")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameServiceTest {
    @Inject
    GameService gameService;

    @InjectMock
    GamesRepository gamesRepository;

    private Game game;

    private Game update;

    @BeforeEach
    void setup() {
        game = new Game(
                1L,
                "Cyberpunk 2077",
                "Em 2077, a América está arruinada. Megacorporações controlam a vida em todos os aspectos, do topo de seus arranha-céus até as atividades ilícitas que acontecem nas ruas. O mundo entre esses dois extremos é onde a decadência, sexo e a cultura popular se misturam com crimes violentos, pobreza extrema e a promessa inatingível do Sonho Americano.Em um mundo em que você não tem futuro, o que importa é ter controle sob o que você é. Para sobreviver e proteger sua independência, você modifica seu corpo com itens cibernéticos e faça trabalhos que jamais ousaria. Escolha viver livremente, independente de sistemas ou controles – você obedece às suas regras. Em Cyberpunk você joga como V – um assassino(a) de alguel em ascensão – e você acabou de conseguir seu primeiro contrato sério. Em um mundo de guerreiros aprimorados cibernéticamente, gênios da tecnologia e lifehackers corporativos, você pode dar o primeiro passo para se tornar uma lenda urbana.",
                LocalDate.of(2020, 12, 10),
                "https://www.youtube.com/watch?v=UnA7tepsc7s"
        );

        update = new Game(
                1L,
                "The Last of Us Part I",
                "Em uma civilização devastada, em que infectados e sobreviventes veteranos estão à solta, Joel, um protagonista abatido, é contratado para tirar uma garota de 14 anos, Ellie, de uma zona de quarentena militar.",
                LocalDate.of(2022, 9, 2),
                "https://www.youtube.com/watch?v=xni05j8SH_I"
        );

    }

    @Test
    @Order(1)
    void shouldReturnGameIfIdIsValid() {
        Mockito.when(gamesRepository.findById(1L)).thenReturn(game);
        Assertions.assertEquals(new GameResponseDTO(game),gameService.findGameById(1L));
    }

    @Test
    @Order(1)
    void shouldThrowExceptionIfReceiveInvalidID() {
        Mockito.when(gamesRepository.findById(null)).thenThrow(GameNotFoundException.class);
        Assertions.assertThrows(GameNotFoundException.class, () -> {
            gameService.findGameById(null);
        });
    }

    @Test
    @Order(2)
    void ShouldCreateGameIfHasValidBody(){
        gameService.createGame(new GameRequestDTO(game));
        Mockito.verify(gamesRepository).persistAndFlush(any(Game.class));
    }

    @Test
    @Order(3)
    void ShouldUpdateGameIfHasValidBody(){
        Mockito.when(gamesRepository.findById(1L)).thenReturn(update);
        gameService.updateGame(1L, new GameRequestDTO(update));
        Mockito.verify(gamesRepository).persistAndFlush(any(Game.class));
    }
    @Test
    @Order(4)
    void shouldDeleteGameIfExists(){
        Mockito.when(gamesRepository.findById(1L)).thenReturn(game);
        gameService.deleteGame(1L);
        Mockito.verify(gamesRepository).delete(any(Game.class));
    }

}