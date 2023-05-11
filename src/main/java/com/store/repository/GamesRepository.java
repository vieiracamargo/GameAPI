package com.store.repository;

import com.store.entity.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GamesRepository implements PanacheRepository<Game> {
}
