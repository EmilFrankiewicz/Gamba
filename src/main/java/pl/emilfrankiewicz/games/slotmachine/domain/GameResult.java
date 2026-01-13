package pl.emilfrankiewicz.games.slotmachine.domain;

import java.time.Instant;
import java.util.List;

public record GameResult(boolean win, List<Symbol> symbols, EvaluationResult evaluation, Instant occurredAt) {
}


