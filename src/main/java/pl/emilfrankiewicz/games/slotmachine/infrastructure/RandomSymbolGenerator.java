package pl.emilfrankiewicz.games.slotmachine.infrastructure;

import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;

import java.util.List;
import java.util.SplittableRandom;

public class RandomSymbolGenerator implements SymbolGenerator {

    private final SplittableRandom random = new SplittableRandom();

    @Override
    public List<Symbol> generateSymbols() {
        Symbol[] values = Symbol.values();

        return List.of(
                values[random.nextInt(values.length)],
                values[random.nextInt(values.length)],
                values[random.nextInt(values.length)]);
    }
}
