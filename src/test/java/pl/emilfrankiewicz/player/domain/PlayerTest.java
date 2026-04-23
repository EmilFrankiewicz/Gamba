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
    void shouldIncreaseBalanceWhenPlayerWins() {
        // given
        Player player = new Player(new PlayerId("1"), new Balance(0));

        // when
        Player updated = player.win(10);

        // then
        assertThat(updated).isNotSameAs(player);
        assertThat(player.getBalance()).isEqualTo(new Balance(0));
        assertThat(updated.getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldDecreaseBalanceWhenPlayerPaysForGame() {
        // given
        Player player = new Player(new PlayerId("1"), new Balance(15));

        // when
        Player updated = player.payForGame(5);

        // then
        assertThat(updated).isNotSameAs(player);
        assertThat(player.getBalance()).isEqualTo(new Balance(15));
        assertThat(updated.getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldNotAllowNegativeBalanceOnPlayer() {
        // given
        Player player = new Player(new PlayerId("1"), new Balance(5));

        // when
        Throwable thrown = catchThrowable(() -> player.payForGame(10));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount cannot be less than 0");

        assertThat(player.getBalance()).isEqualTo(new Balance(5));
    }

    @Test
    void shouldApplyPayoutWhenGreaterThanZero() {
        // given
        Player player = new Player(new PlayerId("1"), new Balance(10));

        // when
        Player updated = player.applyPayout(5);

        // then
        assertThat(updated).isNotSameAs(player);
        assertThat(updated.getBalance()).isEqualTo(new Balance(15));
    }

    @Test
    void shouldNotChangeBalanceWhenPayoutIsZero() {
        // given
        Player player = new Player(new PlayerId("1"), new Balance(10));

        // when
        Player updated = player.applyPayout(0);

        // then
        assertThat(updated).isSameAs(player);
        assertThat(updated.getBalance()).isEqualTo(new Balance(10));
    }
}