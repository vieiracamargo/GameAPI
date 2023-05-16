package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.exception.GameNotFoundException;
import com.store.repository.GamesRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
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
                "Control",
                "Jesse Faden (Courtney Hope), após uma experiência traumática durante sua infância lhe ter concedido poderes sobrenaturais, procura respostas no Departamento Federal de Controle, uma agência governamental clandestina encarregada de estudar e conter fenômenos sobrenaturais.",
                LocalDate.of(2019, 8, 27),
                "https://www.youtube.com/watch?v=xni05j8SH_I"
        );

    }

    @Test
    void shouldReturnGameIfIdIsValid() {
        Mockito.when(gamesRepository.findById(1L)).thenReturn(game);
        Assertions.assertEquals(gameService.findGameById(1L), new GameResponseDTO(game));
    }

    @Test
    void shouldThrowExceptionIfReceiveInvalidID() {
        Mockito.when(gamesRepository.findById(null)).thenThrow(GameNotFoundException.class);
        Assertions.assertThrows(GameNotFoundException.class, () -> {
            gameService.findGameById(null);
        });
    }

    @Test
    void ShouldCreateGameIfHasValidBody(){
        gameService.createGame(new GameRequestDTO(game));
        Mockito.verify(gamesRepository).persistAndFlush(any(Game.class));
    }

    @Test
    void ShouldUpdateGameIfHasValidBody(){
        Mockito.when(gamesRepository.findById(1L)).thenReturn(game);
        gameService.updateGame(1L, new GameRequestDTO(update));
        Mockito.verify(gamesRepository).persistAndFlush(any(Game.class));
        Assertions.assertNotEquals(game,update);
    }
    @Test
    void shouldDeleteGameIfExists(){
        Mockito.when(gamesRepository.findById(1L)).thenReturn(game);
        gameService.deleteGame(1L);
        Mockito.verify(gamesRepository).delete(game);
    }

}