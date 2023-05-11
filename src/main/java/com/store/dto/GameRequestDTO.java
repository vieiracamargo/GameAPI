package com.store.dto;

import com.store.entity.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record GameRequestDTO(
        @NotBlank(message = "title can not be empty")
        String title,

        @NotBlank(message = "description can not be empty")
        String description,

        @NotNull(message = "releaseDate can not be empty")
        @PastOrPresent(message = "releaseDate must be a valid date")
        LocalDate releaseDate,

        @NotBlank(message = "trailerUrl can not be empty")
        String trailerUrl
) {
    public Game toEntity(){
        return new Game(
                null,
                this.title,
                this.description,
                this.releaseDate,
                this.trailerUrl
        );
    }
}
