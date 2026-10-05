package pl.emilfrankiewicz.game.history.domain;

import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.time.Instant;
import java.util.UUID;

public class GameHistoryEntry {

    private final GameHistoryId id;
    private final PlayerId playerId;
    private final Instant occurredAt;
    private final GameType gameType;
    private final String details;
    private final Amount cost;
    private final Amount payout;
    private final Amount balanceBefore;
    private final Amount balanceAfter;

    public GameHistoryEntry(GameHistoryId id, PlayerId playerId, Instant occurredAt, GameType gameType, String details, Amount cost, Amount payout, Amount balanceBefore, Amount balanceAfter) {
        this.id = id;
        this.playerId = playerId;
        this.occurredAt = occurredAt;
        this.gameType = gameType;
        this.details = details;
        this.cost = cost;
        this.payout = payout;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
    }

    public GameHistoryEntry(PlayerId playerId, Instant occurredAt, GameType gameType, String details, Amount cost, Amount payout, Amount balanceBefore, Amount balanceAfter) {
        this.id = null;
        this.playerId = playerId;
        this.occurredAt = occurredAt;
        this.gameType = gameType;
        this.details = details;
        this.cost = cost;
        this.payout = payout;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
    }

    public static GameHistoryEntry of(
            PlayerId playerId,
            GameOutcome outcome,
            Amount cost,
            Amount balanceBefore,
            Amount balanceAfter
    ) {
        return new GameHistoryEntry(
                new GameHistoryId(UUID.randomUUID().toString()),
                playerId,
                outcome.occurredAt(),
                outcome.gameType(),
                outcome.details(),
                cost,
                outcome.payout(),
                balanceBefore,
                balanceAfter
        );
    }
    public GameHistoryEntry withId(GameHistoryId id) {
        return new GameHistoryEntry(id, this.playerId, this.occurredAt, this.gameType, this.details, this.cost, this.payout, this.balanceBefore, this.balanceAfter);
    }

    public GameHistoryId getId() {
        return id;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public GameType getGameType() {
        return gameType;
    }

    public String getDetails() {
        return details;
    }

    public Amount getCost() {
        return cost;
    }

    public Amount getPayout() {
        return payout;
    }

    public Amount getBalanceBefore() {
        return balanceBefore;
    }

    public Amount getBalanceAfter() {
        return balanceAfter;
    }
}
