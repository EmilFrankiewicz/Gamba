package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

}