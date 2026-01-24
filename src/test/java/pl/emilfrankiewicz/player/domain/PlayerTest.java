package pl.emilfrankiewicz.player.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PlayerTest {

    @Test
    void shouldReturnPlayerBalance() {
        // given
        Balance balance = new Balance(0);
        Player player = new Player(new PlayerId("1"), balance);

        // when
        Balance result = player.getBalance();

        // then
        assertThat(result).isEqualTo(balance);
    }

    @Test
    void shouldIncreasePlayerBalance() {
        // given
        Balance balance = new Balance(0);
        Player player = new Player(new PlayerId("1"), balance);

        // when
        Player updated = player.increase(10);

        // then
        assertThat(updated).isNotEqualTo(player);
        assertThat(player.getBalance()).isEqualTo(balance);
        assertThat(updated.getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldDecreasePlayerBalance() {
        // given
        Balance balance = new Balance(15);
        Player player = new Player(new PlayerId("1"), balance);

        // when
        Player updated = player.decrease(5);

        // then
        assertThat(updated).isNotEqualTo(player);
        assertThat(player.getBalance()).isEqualTo(balance);
        assertThat(updated.getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldNotAllowNegativeBalanceOnPlayer() {
        // given
        Balance balance = new Balance(5);
        Player player = new Player(new PlayerId("1"), balance);

        // when
        Throwable thrown = catchThrowable(() -> player.decrease(10));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Amount cannot be less than 0");
        assertThat(player.getBalance()).isEqualTo(balance);
    }
}

