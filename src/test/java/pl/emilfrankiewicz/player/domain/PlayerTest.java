package pl.emilfrankiewicz.player.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}

