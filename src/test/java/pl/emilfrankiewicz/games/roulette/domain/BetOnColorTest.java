package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BetOnColorTest {

    @Test
    void shouldWinWhenBetColorMatchesResult() {
        //given
        BetOnColor betOnColor = new BetOnColor(Color.RED);
        RouletteResult rouletteResult = new RouletteResult(1);

        //when
        boolean result = betOnColor.checkIfWin(rouletteResult);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldLoseWhenBetColorDoesNotMatchResult() {
        //given
        BetOnColor betOnColor = new BetOnColor(Color.BLACK);
        RouletteResult rouletteResult = new RouletteResult(1);

        //when
        boolean result = betOnColor.checkIfWin(rouletteResult);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenColorIsNull() {
        //when
        Throwable thrown = catchThrowable(() -> new BetOnColor(null));

        //then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Color can't be null");
    }

    @Test
    void shouldThrowExceptionWhenColorIsGreen() {
        //when
        Throwable thrown = catchThrowable(() -> new BetOnColor(Color.GREEN));

        //then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Only RED or BLACK colors is allowed");
    }
}