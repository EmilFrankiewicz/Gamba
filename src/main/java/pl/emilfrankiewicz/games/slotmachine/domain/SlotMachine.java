package pl.emilfrankiewicz.games.slotmachine.domain;

import java.util.List;
import java.util.Objects;

class SlotMachine {

    EvaluationResult evaluate(List<Symbol> symbols) {
        return new EvaluationResult(payoutChecker(symbols));
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
        if (symbols.size() != 3 || symbols.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasAllSymbolsSame(List<Symbol> symbols) {
        return symbols.stream().distinct().count() == 1;
    }
}
