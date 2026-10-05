package pl.emilfrankiewicz.game.history.domain;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class GameHistoryEntryTest {

    @Test
    void shouldContainCorrectValues() {
        // given
        final GameHistoryId id = new GameHistoryId("1");
        final PlayerId playerId = new PlayerId("1");
        final Instant timestamp = Instant.parse("2024-01-01T00:00:00Z");
        final GameType gameType = GameType.SLOT;
        final String details = "symbols=[SEVEN,BAR,SEVEN],category=HIGH";
        final Amount cost = new Amount(25);
        final Amount payout = new Amount(50);
        final Amount balanceBefore = new Amount(100);
        final Amount balanceAfter = new Amount(125);

        // when
        GameHistoryEntry gameHistoryEntry = new GameHistoryEntry(
                id,
                playerId,
                timestamp,
                gameType,
                details,
                cost,
                payout,
                balanceBefore,
                balanceAfter
        );

        // then
        assertThat(gameHistoryEntry.getId()).isEqualTo(id);
        assertThat(gameHistoryEntry.getPlayerId()).isEqualTo(playerId);
        assertThat(gameHistoryEntry.getOccurredAt()).isEqualTo(timestamp);
        assertThat(gameHistoryEntry.getGameType()).isEqualTo(gameType);
        assertThat(gameHistoryEntry.getDetails()).isEqualTo(details);
        assertThat(gameHistoryEntry.getCost()).isEqualTo(cost);
        assertThat(gameHistoryEntry.getPayout()).isEqualTo(payout);
        assertThat(gameHistoryEntry.getBalanceBefore()).isEqualTo(balanceBefore);
        assertThat(gameHistoryEntry.getBalanceAfter()).isEqualTo(balanceAfter);
    }
}