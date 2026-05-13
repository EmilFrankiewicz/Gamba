package pl.emilfrankiewicz.games.roulette.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorTest {

    @Test
    void shouldReturnGreenForZero() {
        //given
        int number = 0;

        //when
        Color result = Color.colorOf(0);

        //then
        assertThat(result).isEqualTo(Color.GREEN);
    }

    @Test
    void shouldReturnRedForOne() {
        //given
        int number = 1;

        //when
        Color result = Color.colorOf(1);

        //then
        assertThat(result).isEqualTo(Color.RED);
    }

    @Test
    void shouldReturnBlackForTwo() {
        //given
        int number = 2;

        //when
        Color result = Color.colorOf(2);

        //then
        assertThat(result).isEqualTo(Color.BLACK);
    }
}