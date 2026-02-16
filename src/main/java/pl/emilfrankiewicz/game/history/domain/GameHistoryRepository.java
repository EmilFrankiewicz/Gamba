package pl.emilfrankiewicz.game.history.domain;

import pl.emilfrankiewicz.player.domain.PlayerId;

import java.util.List;

public interface GameHistoryRepository {
    void save(GameHistoryEntry entry);

    List<GameHistoryEntry> findByPlayerId(PlayerId id);

    List<GameHistoryEntry> findAll();
}
