package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BetOnColorTest {

    @Test
    void shouldWinWhenBetColorMatchesResult() {
        //given
        BetOnColor betOnColor = new BetOnColor(Color.GREEN);
        RouletteResult rouletteResult = new RouletteResult(0);

        //when
        boolean result = betOnColor.checkIfWin(rouletteResult);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldLoseWhenBetColorDoesNotMatchResult() {
        //given
        BetOnColor betOnColor = new BetOnColor(Color.GREEN);
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
}