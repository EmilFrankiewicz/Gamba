package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.Amount;

import static org.assertj.core.api.Assertions.assertThat;

class PayoutCalculatorTest {

    @Test
    void shouldCalculateCorrectPayout() {
        //given
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        Bet bet = new BetOnNumber(1);

        //when
        Amount result = payoutCalculator.calculatePayout(new Amount(10), bet);

        //then
        assertThat(result.getAmount()).isEqualTo(350);
    }
}