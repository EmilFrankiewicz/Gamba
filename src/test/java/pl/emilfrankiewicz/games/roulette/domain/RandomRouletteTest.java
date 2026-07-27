package pl.emilfrankiewicz.games.roulette.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RandomRouletteTest {

    @Test
    void shouldReturnNumberFrom0To36() {
        //given
        RandomRoulette randomRoulette = new RandomRoulette();

        //when
        RouletteResult number = randomRoulette.spin();

        //then
        assertThat(number.getNumber()).isBetween(0, 36);
    }
}