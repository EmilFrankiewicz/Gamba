package pl.emilfrankiewicz.game.history.domain;

import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.time.Instant;

public class GameHistoryEntry {

    private final GameHistoryId id;
    private final PlayerId playerId;
    private final Instant timestamp;
    private final GameType gameType;
    private final String details;
    private final int cost;
    private final int payout;
    private final int balanceBefore;
    private final int balanceAfter;

    public GameHistoryEntry(GameHistoryId id, PlayerId playerId, Instant timestamp, GameType gameType, String details, int cost, int payout, int balanceBefore, int balanceAfter) {
        this.id = id;
        this.playerId = playerId;
        this.timestamp = timestamp;
        this.gameType = gameType;
        this.details = details;
        this.cost = cost;
        this.payout = payout;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
    }

    public GameHistoryId getId() {
        return id;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Instant getTimestamp() {
        return timestamp;
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
