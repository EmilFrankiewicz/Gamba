package pl.emilfrankiewicz.games.slotmachine.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.*;


public class SlotMachineTest {

    SlotMachine slotMachine = new SlotMachine();

    @Test
    void shouldReturnVeryHighWinWhenThreeSevens() {
        // given
        List<Symbol> symbols = List.of(SEVEN, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(100);
        assertThat(WinCategory.VERY_HIGH).isEqualTo(result.winCategory());
    }

    @Test
    void shouldReturnHighWinWhenThreeBars() {
        // given
        List<Symbol> symbols = List.of(BAR, BAR, BAR);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(50);
        assertThat(WinCategory.HIGH).isEqualTo(result.winCategory());
    }

    @Test
    void shouldReturnMediumWinWhenThreeCherries() {
        // given
        List<Symbol> symbols = List.of(CHERRY, CHERRY, CHERRY);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(20);
        assertThat(WinCategory.MEDIUM).isEqualTo(result.winCategory());
    }

    @Test
    void shouldReturnLowWinWhenThreeLemons() {
        // given
        List<Symbol> symbols = List.of(LEMON, LEMON, LEMON);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(10);
        assertThat(WinCategory.LOW).isEqualTo(result.winCategory());
    }

    @Test
    void shouldReturnVeryLowWinWhenThreePlums() {
        // given
        List<Symbol> symbols = List.of(PLUM, PLUM, PLUM);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(5);
        assertThat(WinCategory.VERY_LOW).isEqualTo(result.winCategory());
    }

    @Test
    void shouldLoseWhenNotAllSymbolsSame() {
        // given
        List<Symbol> symbols = List.of(PLUM, SEVEN, SEVEN);

        // when
        var result = slotMachine.evaluate(symbols);

        // then
        assertThat(result.payout()).isEqualTo(0);
        assertThat(WinCategory.LOSS).isEqualTo(result.winCategory());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsNull() {
        // given
        List<Symbol> symbols = null;

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot be null");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsEmpty() {
        // given
        List<Symbol> symbols = List.of();

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot be empty");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSymbolsListIsNotExactlyThree() {
        // given
        List<Symbol> symbols = List.of(SEVEN, SEVEN);

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Exactly three symbols are required");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSymbolsListContainsNull() {
        // given
        List<Symbol> symbols = Arrays.asList(SEVEN, null, SEVEN);

        // when
        Throwable thrown = catchThrowable(() -> slotMachine.evaluate(symbols));

        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Symbols list cannot contain null values");
    }

}
