package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PayoutCalculatorTest {

    @Test
    void shouldCalculateCorrectPayout() {
        //given
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        Bet bet = new BetOnNumber(1);

        //when
        int result = payoutCalculator.calculatePayout(10, bet);

        //then
        assertThat(result).isEqualTo(350);
    }
}