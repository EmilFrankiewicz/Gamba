package pl.emilfrankiewicz.player.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BalanceTest {

    @Test
    void shouldIncreaseAmountAndReturnNewBalance() {
        // given
        Balance balance = new Balance(0);

        // when
        Balance result = balance.increase(10);

        // then
        assertThat(result.getAmount()).isEqualTo(10);
        assertThat(balance.getAmount()).isEqualTo(0);
        assertThat(balance).isNotEqualTo(result);
    }

    @Test
    void shouldDecreaseAmountAndReturnNewBalance() {
        // given
        Balance balance = new Balance(20);

        // when
        Balance result = balance.decrease(5);

        // then
        assertThat(result.getAmount()).isEqualTo(15);
        assertThat(balance.getAmount()).isEqualTo(20);
        assertThat(balance).isNotEqualTo(result);
    }

    @Test
    void shouldNotAllowNegativeBalance() {
        // given
        Balance balance = new Balance(10);

        // when
        Throwable thrown = catchThrowable(() -> balance.decrease(20));

        // then
        assertThat(balance.getAmount()).isEqualTo(10);
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Amount cannot be less than 0");
    }
}


