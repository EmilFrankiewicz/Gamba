package pl.emilfrankiewicz.games.roulette.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RouletteResultTest {

    @Test
    void shouldCreateGreenResultForZero() {
        //given && when
        RouletteResult result = new RouletteResult(0);

        //then
        assertThat(result.getColor()).isEqualTo(Color.GREEN);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 100, 37, 100})
    void shouldThrowExceptionWhenNumberIsNotInRange(int number) {
        Throwable thrown = catchThrowable(() -> new RouletteResult(number));

        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not in range 0-37");
    }

}