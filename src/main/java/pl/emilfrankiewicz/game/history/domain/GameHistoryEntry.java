package pl.emilfrankiewicz.game.history.domain;

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
    private final int cost;
    private final int payout;
    private final int balanceBefore;
    private final int balanceAfter;

    public GameHistoryEntry(GameHistoryId id, PlayerId playerId, Instant occurredAt, GameType gameType, String details, int cost, int payout, int balanceBefore, int balanceAfter) {
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

    public GameHistoryEntry(PlayerId playerId, Instant occurredAt, GameType gameType, String details, int cost, int payout, int balanceBefore, int balanceAfter) {
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
            int cost,
            int balanceBefore,
            int balanceAfter
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

    public GameHistoryId id() {
        return id;
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

    public int getCost() {
        return cost;
    }

    public int getPayout() {
        return payout;
    }

    public int getBalanceBefore() {
        return balanceBefore;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }
}
