package pl.emilfrankiewicz.games.slotmachine.domain;

import pl.emilfrankiewicz.game.domain.BasePayout;

import java.time.Instant;
import java.util.List;

public record GameResult(boolean win, List<Symbol> symbols, EvaluationResult evaluation,
                         Instant occurredAt) implements BasePayout {
    @Override
    public int amount() {
        return evaluation.payout();
    }
}


