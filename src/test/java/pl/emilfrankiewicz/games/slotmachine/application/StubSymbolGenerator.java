package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;

import java.util.List;

import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.*;

public class StubSymbolGenerator implements SymbolGenerator {
    @Override
    public List<Symbol> generateSymbols() {
        return List.of(SEVEN, SEVEN, SEVEN);
    }
}
