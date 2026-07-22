package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetEvaluatorTest {

    @Test
    void shouldReturnWinningBetResult() {
        //given
        BetEvaluator betEvaluator = new BetEvaluator();
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        RouletteResult rouletteResult = new RouletteResult(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();

        //when
        BetResult result = betEvaluator.evaluate(placedBet, rouletteResult, payoutCalculator);

        //then
        assertThat(result.isWin()).isTrue();
        assertThat(result.winAmount()).isEqualTo(new Amount(350));
    }

    @Test
    void shouldReturnLosingBetResult() {
        //given
        BetEvaluator betEvaluator = new BetEvaluator();
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        RouletteResult rouletteResult = new RouletteResult(2);
        PayoutCalculator payoutCalculator = new PayoutCalculator();

        //when
        BetResult result = betEvaluator.evaluate(placedBet, rouletteResult, payoutCalculator);

        //then
        assertThat(result.isWin()).isFalse();
        assertThat(result.winAmount()).isEqualTo(new Amount(0));
    }
}