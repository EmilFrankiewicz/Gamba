package pl.emilfrankiewicz.games.slotmachine.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
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
        assertEquals(WinCategory.VERY_HIGH, result.winCategory());
    }

    @Test
    public void shouldReturnHighWinWhenThreeBars() {
        // given
        List<Symbol> symbols = List.of(BAR, BAR, BAR);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(50, result.payout());
        assertEquals(WinCategory.HIGH, result.winCategory());
    }

    @Test
    public void shouldReturnMediumWinWhenThreeCherries() {
        // given
        List<Symbol> symbols = List.of(CHERRY, CHERRY, CHERRY);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(20, result.payout());
        assertEquals(WinCategory.MEDIUM, result.winCategory());
    }

    @Test
    public void shouldReturnLowWinWhenThreeLemons() {
        // given
        List<Symbol> symbols = List.of(LEMON, LEMON, LEMON);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(10, result.payout());
        assertEquals(WinCategory.LOW, result.winCategory());
    }

    @Test
    public void shouldReturnVeryLowWinWhenThreePlums() {
        // given
        List<Symbol> symbols = List.of(PLUM, PLUM, PLUM);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(5, result.payout());
        assertEquals(WinCategory.VERY_LOW, result.winCategory());
    }

    @Test
    public void shouldLoseWhenNotAllSymbolsSame() {
        // given
        List<Symbol> symbols = List.of(PLUM, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertEquals(0, result.payout());
        assertEquals(WinCategory.LOSS, result.winCategory());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsNull() {
        // given
        List<Symbol> symbols = null;

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot be null");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsEmpty() {
        // given
        List<Symbol> symbols = List.of();

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot be empty");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsNotExactlyThree() {
        // given
        List<Symbol> symbols = List.of(SEVEN, SEVEN);

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Exactly three symbols are required");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSymbolsListContainsNull() {
        // given
        List<Symbol> symbols = Arrays.asList(SEVEN, null, SEVEN);

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot contain null values");
    }

}
