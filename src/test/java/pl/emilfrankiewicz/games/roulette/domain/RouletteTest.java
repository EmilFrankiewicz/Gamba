package pl.emilfrankiewicz.games.roulette.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RouletteTest {

    @Test
    void shouldReturnNumberFrom0To36() {
        //given
        Roulette roulette = new Roulette();

        //when
        int number = roulette.spin();

        //then
        assertThat(number).isBetween(0, 36);
    }
}