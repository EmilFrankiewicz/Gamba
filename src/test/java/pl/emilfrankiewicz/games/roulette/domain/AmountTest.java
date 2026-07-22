package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class AmountTest {

    @Test
    void shouldBeEqualWhenAmountsHaveSameValue() {
        //given & when
        Amount amount = new Amount(10);
        Amount amount2 = new Amount(10);

        //then
        assertThat(amount).isEqualTo(amount2);
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        //given & when
        Throwable thrown = catchThrowable(() -> new Amount(-10));

        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Amount cannot be less than 0");
    }
}