package pl.emilfrankiewicz.games.slotmachine.domain;

import java.util.List;

public record SpinOutcome(List<Symbol> symbols, EvaluationResult evaluation) {
}
