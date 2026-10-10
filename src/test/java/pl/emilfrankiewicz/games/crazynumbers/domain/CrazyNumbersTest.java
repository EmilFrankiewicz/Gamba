package pl.emilfrankiewicz.games.crazynumbers.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CrazyNumbersTest {

    @Test
    void shouldSum10numbersAndReturnResult51() {
        //given
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        List<Integer> listOfNumbers = List.of(7, 4, 10, 3, 8, 5, 3, 6, 0, 5);

        //when
        int sum = crazyNumbers.sum(listOfNumbers);

        //then
        assertThat(sum).isEqualTo(51);
    }
}