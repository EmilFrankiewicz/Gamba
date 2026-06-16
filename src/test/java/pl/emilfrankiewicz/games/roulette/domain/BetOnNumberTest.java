package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetOnNumberTest {

    @Test
    void shouldWinWhenBetNumberMatchesResult() {
        //given
        BetOnNumber betOnNumber = new BetOnNumber(17);
        RouletteResult rouletteResult = new RouletteResult(17);

        //when
        boolean result = betOnNumber.checkIfWin(rouletteResult);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldLoseWhenBetNumberDoesNotMatchResult() {
        //given
        BetOnNumber betOnNumber = new BetOnNumber(17);
        RouletteResult rouletteResult = new RouletteResult(18);

        //when
        boolean result = betOnNumber.checkIfWin(rouletteResult);

        //then
        assertThat(result).isFalse();
    }
}
