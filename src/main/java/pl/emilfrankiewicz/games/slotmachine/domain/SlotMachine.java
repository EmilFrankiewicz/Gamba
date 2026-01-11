package pl.emilfrankiewicz.games.slotmachine.domain;

import java.util.List;
import java.util.Objects;

public class SlotMachine {

   public EvaluationResult evaluate(List<Symbol> symbols) {
        int payout = payoutChecker(symbols);
        return new EvaluationResult(payout, WinCategory.fromPayout(payout));
    }

    private int payoutChecker(List<Symbol> symbols) {
        hasExactlyThreeSymbols(symbols);
        if (!hasAllSymbolsSame(symbols)) {
            return 0;
        }

        Symbol symbol = symbols.get(0);

        return switch (symbol) {
            case SEVEN -> 100;
            case BAR -> 50;
            case CHERRY -> 20;
            case LEMON -> 10;
            case PLUM -> 5;
        };
    }

    private void hasExactlyThreeSymbols(List<Symbol> symbols) {
        if (symbols == null) {
            throw new IllegalArgumentException("Symbols list cannot be null");
        }
        if (symbols.isEmpty()) {
            throw new IllegalArgumentException("Symbols list cannot be empty");
        }
        if (symbols.size() != 3) {
            throw new IllegalArgumentException("Exactly three symbols are required");
        }
        if (symbols.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Symbols list cannot contain null values");
        }
    }

    private boolean hasAllSymbolsSame(List<Symbol> symbols) {
        return symbols.stream().distinct().count() == 1;
    }
}
