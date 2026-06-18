package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetOnEvenTest {

    @Test
    void shouldWinWhenBetOnEvenMatchesResult() {
        //given
        BetOnEven betOnEven = new BetOnEven();
        RouletteResult rouletteResult = new RouletteResult(2);

        //when
        boolean result = betOnEven.checkIfWin(rouletteResult);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldLoseWhenBetOnEvenDoesNotMatchResult() {
        //given
        BetOnEven betOnEven = new BetOnEven();
        RouletteResult rouletteResult = new RouletteResult(1);

        //when
        boolean result = betOnEven.checkIfWin(rouletteResult);

        //then
        assertThat(result).isFalse();
    }
}