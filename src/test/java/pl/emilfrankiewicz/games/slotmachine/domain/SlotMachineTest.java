package pl.emilfrankiewicz.games.slotmachine.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.SEVEN;

public class SlotMachineTest {

    SlotMachine slotMachine = new SlotMachine();


    @Test
    public void shouldWinBigWhenThreeSevens() {
        // given
        List<Symbol> symbols = List.of(SEVEN, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(100, result.payout());
    }

    @Test
    public void shouldLoseWhenAllSymbolsDifferent() {
    }
}
