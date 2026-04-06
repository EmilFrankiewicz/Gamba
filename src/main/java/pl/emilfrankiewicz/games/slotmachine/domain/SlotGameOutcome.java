package pl.emilfrankiewicz.games.slotmachine.domain;

import pl.emilfrankiewicz.game.domain.GameType;

import java.time.Instant;

public record SlotGameOutcome(boolean win, Instant occurredAt, GameType gameType, String details, int payout) {
}
