package pl.emilfrankiewicz.games.crazynumbers.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.emilfrankiewicz.game.domain.Amount;

import static org.assertj.core.api.Assertions.assertThat;

class PayoutCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "TEN, 20",
            "HUNDRED, 200",
            "THOUSAND, 2000",
            "TEN_THOUSAND, 20000"
    })
    void shouldReturnCorrectPayoutForEachWinBet(CrazyNumbersBet bet, int expectedPayout) {
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        Amount payout = payoutCalculator.calculatePayout(bet, true);
        assertThat(payout).isEqualTo(new Amount(expectedPayout));
    }

    @Test
    void shouldReturnAmount0WhenLossAndBetIsTen() {
        //given
        PayoutCalculator payoutCalculator = new PayoutCalculator();

        //when
        Amount amount = payoutCalculator.calculatePayout(CrazyNumbersBet.TEN, false);

        //then
        assertThat(amount).isEqualTo(new Amount(0));
    }
}