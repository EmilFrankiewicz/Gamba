package pl.emilfrankiewicz.game.domain;

import java.time.Instant;

public record GameOutcome(boolean win, Instant occurredAt, GameType gameType, String details, int basePayout,
                          int finalPayout) {
}
