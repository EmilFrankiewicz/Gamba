package pl.emilfrankiewicz.game.history.infrastructure;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.game.history.domain.GameHistoryEntry;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryGameHistoryRepositoryTest {

    private final InMemoryGameHistoryRepository repository = new InMemoryGameHistoryRepository();

    @Test
    void shouldSaveAndRetrieveEntries() {
        // given
        GameHistoryEntry entry = new GameHistoryEntry(
                new PlayerId("1"),
                Instant.parse("2024-01-01T00:00:00Z"),
                GameType.SLOT,
                "symbols=[SEVEN,BAR,SEVEN],category=HIGH",
                25, 50, 100, 125
        );

        // when
        repository.save(entry);
        List<GameHistoryEntry> result = repository.findByPlayerId(new PlayerId("1"));

        // then
        assertThat(result).hasSize(1);
        GameHistoryEntry saved = result.get(0);

        assertThat(saved.getId()).isNotNull();
    }


    @Test
    void shouldFindEntriesByPlayerId() {
        // given
        GameHistoryEntry entry1 = entry("1", "2024-01-01T00:00:00Z");
        GameHistoryEntry entry2 = entry("2", "2024-01-01T00:00:00Z");
        GameHistoryEntry entry3 = entry("3", "2024-01-01T00:00:00Z");
        GameHistoryEntry entry4 = entry("1", "2024-01-01T00:00:00Z");

        repository.save(entry1);
        repository.save(entry2);
        repository.save(entry3);
        repository.save(entry4);

        // when
        List<GameHistoryEntry> result = repository.findByPlayerId(new PlayerId("1"));

        // then
        assertThat(result)
                .extracting(GameHistoryEntry::getPlayerId)
                .containsExactly(new PlayerId("1"), new PlayerId("1"));

    }

    @Test
    void shouldReturnEmptyListWhenNoEntries() {
        // when
        List<GameHistoryEntry> result = repository.findAll();

        // then
        assertThat(result).isEmpty();
    }

    private GameHistoryEntry entry(String playerId, String timestamp) {
        return new GameHistoryEntry(
                new PlayerId(playerId),
                Instant.parse(timestamp),
                GameType.SLOT,
                "symbols=[SEVEN,BAR,SEVEN],category=HIGH",
                25, 50, 100, 125
        );
    }
}
