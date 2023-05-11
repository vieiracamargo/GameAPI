package com.store.service;

import com.store.dto.GameRequestDTO;
import com.store.dto.GameResponseDTO;
import com.store.entity.Game;
import com.store.exception.GameNotFoundException;
import com.store.repository.GamesRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@ApplicationScoped
public class GameService {
    private final GamesRepository gamesRepository;

    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Transactional
    public GameResponseDTO createGame(@Valid GameRequestDTO gameRequestDTO) {
        Game entity = gameRequestDTO.toEntity();
        gamesRepository.persistAndFlush(entity);
        return new GameResponseDTO(entity);
    }

    @Transactional
    public GameResponseDTO findGameById(Long id) {
        Game game = gamesRepository.findById(id);

        if (game == null) {
            throw new GameNotFoundException("Could not find this game in the database");
        }

        return new GameResponseDTO(game);
    }

    @Transactional
    public List<GameResponseDTO> findAllGames(Page pageble, String sort, Sort.Direction direction){
        List < Game > list = gamesRepository.findAll(Sort.by(sort, direction))
                .page(pageble)
                .stream()
                .toList();
        return list.stream().map(GameResponseDTO::new).toList();
    }
}
