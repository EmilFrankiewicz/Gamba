package pl.emilfrankiewicz.games.slotmachine.infrastructure;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomSymbolGeneratorTest {

    RandomSymbolGenerator randomSymbolGenerator = new RandomSymbolGenerator();

    @Test
    void shouldGenerateThreeSymbols() {
        // given && when
        List<Symbol> randomList = randomSymbolGenerator.generateSymbols();

        // then
        assertThat(randomList).hasSize(3);
    }

    @Test
    void shouldGenerateNonNullSymbols() {
        // given && when
        List<Symbol> randomList = randomSymbolGenerator.generateSymbols();

        // then
        assertThat(randomList).doesNotContainNull();
    }


    @Test
    void shouldGenerateSymbolsFromEnum() {
        // given && when
        List<Symbol> randomList = randomSymbolGenerator.generateSymbols();
        List<Symbol> allowed = List.of(Symbol.values());

        // then
        assertThat(randomList).allMatch(allowed::contains);
    }
}