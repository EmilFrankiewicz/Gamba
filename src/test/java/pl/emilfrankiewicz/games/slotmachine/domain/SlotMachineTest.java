package pl.emilfrankiewicz.games.slotmachine.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.*;

public class SlotMachineTest {

    SlotMachine slotMachine = new SlotMachine();

    @Test
    public void shouldReturnVeryHighWinWhenThreeSevens() {
        // given
        List<Symbol> symbols = List.of(SEVEN, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(100, result.payout());
    }

    @Test
    public void shouldReturnHighWinWhenThreeBars() {
        // given
        List<Symbol> symbols = List.of(BAR, BAR, BAR);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(50, result.payout());
    }

    @Test
    public void shouldReturnMediumWinWhenThreeCherries() {
        // given
        List<Symbol> symbols = List.of(CHERRY, CHERRY, CHERRY);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(20, result.payout());
    }

    @Test
    public void shouldReturnLowWinWhenThreeLemons() {
        // given
        List<Symbol> symbols = List.of(LEMON, LEMON, LEMON);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(10, result.payout());
    }

    @Test
    public void shouldReturnVeryLowWinWhenThreePlums() {
        // given
        List<Symbol> symbols = List.of(PLUM, PLUM, PLUM);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(5, result.payout());
    }


    @Test
    public void shouldLoseWhenNotAllSymbolsSame() {
        // given
        List<Symbol> symbols = List.of(PLUM, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(0, result.payout());
    }
}
