package pl.emilfrankiewicz.games.slotmachine.infrastructure;

import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;

import java.util.List;

public interface SymbolGenerator {
    List<Symbol> generateSymbols();
}
