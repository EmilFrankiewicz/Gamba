package pl.emilfrankiewicz.game.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BetTest {

    @ParameterizedTest
    @CsvSource({
            "ONE, 1",
            "TWO, 2",
            "FIVE, 5",
            "TEN, 10"
    })
    void shouldReturnCorrectMultiplierForEachBet(Bet bet, int expectedMultiplier) {
        assertThat(bet.multiplier()).isEqualTo(expectedMultiplier);
    }
}
