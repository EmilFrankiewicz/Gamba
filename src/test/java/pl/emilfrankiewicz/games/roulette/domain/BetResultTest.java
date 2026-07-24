package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetResultTest {

    @Test
    void shouldReturnTotalPayoutWhenBetIsWinning() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        BetResult result = new BetResult(true, new Amount(350), placedBet, new RouletteResult(1));

        //then
        assertThat(result.payout()).isEqualTo(new Amount(360));
    }

    @Test
    void shouldReturnZeroPayoutWhenBetIsLosing() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        BetResult result = new BetResult(false, new Amount(0), placedBet, new RouletteResult(2));

        //then
        assertThat(result.payout()).isEqualTo(new Amount(0));
    }
}