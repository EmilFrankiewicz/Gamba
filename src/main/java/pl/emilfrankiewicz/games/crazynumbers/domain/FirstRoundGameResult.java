package pl.emilfrankiewicz.games.crazynumbers.domain;

import java.time.Instant;

public record FirstRoundGameResult(CrazyNumbersFirstRoundResult crazyNumbersFirstRoundResult, Instant occurredAt) {
}