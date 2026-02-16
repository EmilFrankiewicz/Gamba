package pl.emilfrankiewicz.game.history.infrastructure;

import pl.emilfrankiewicz.game.history.domain.GameHistoryEntry;
import pl.emilfrankiewicz.game.history.domain.GameHistoryRepository;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGameHistoryRepository implements GameHistoryRepository {

    private final List<GameHistoryEntry> entries = new ArrayList<>();

    @Override
    public void save(GameHistoryEntry entry) {
        entries.add(entry);
    }

    @Override
    public List<GameHistoryEntry> findByPlayerId(PlayerId id) {
        return entries.stream()
                .filter(e -> e.getPlayerId().equals(id))
                .toList();
    }

    @Override
    public List<GameHistoryEntry> findAll() {
        return List.copyOf(entries);
    }
}

