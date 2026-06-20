package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetOnOddTest {

    @Test
    void shouldWinWhenBetOnOddMatchesResult() {
        //given
        BetOnOdd betOnOdd = new BetOnOdd();
        RouletteResult rouletteResult = new RouletteResult(1);

        //when
        boolean result = betOnOdd.checkIfWin(rouletteResult);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldLoseWhenBetOnOddDoesNotMatchResult() {
        //given
        BetOnOdd betOnOdd = new BetOnOdd();
        RouletteResult rouletteResult = new RouletteResult(2);

        //when
        boolean result = betOnOdd.checkIfWin(rouletteResult);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldLoseWhenResultIsZero()
    {
        //given
        BetOnOdd betOnOdd = new BetOnOdd();
        RouletteResult rouletteResult = new RouletteResult(0);

        //when
        boolean result = betOnOdd.checkIfWin(rouletteResult);

        //then
        assertThat(result).isFalse();
    }
}
