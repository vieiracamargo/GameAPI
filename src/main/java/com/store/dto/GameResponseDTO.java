package com.store.dto;

import com.store.entity.Game;

import java.time.LocalDate;

public record GameResponseDTO(
        Long id,

        String title,

        String description,

        LocalDate releaseDate,

        String trailerUrl
) {
    public GameResponseDTO(Game game){
        this(
                game.getId(),
                game.getTitle(),
                game.getDescription(),
                game.getReleaseDate(),
                game.getTrailerUrl()
        );
    }
}
