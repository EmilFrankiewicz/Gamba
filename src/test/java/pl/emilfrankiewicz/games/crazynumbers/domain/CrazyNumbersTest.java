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

    @Test
    void shouldReturnTrueWhenWin() {
        //given
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        int sum = 51;
        int threshold = 50;

        //when
        boolean result = crazyNumbers.checkIfWin(sum, threshold);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenLoss() {
        //given
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        int sum = 50;
        int threshold = 50;

        //when
        boolean result = crazyNumbers.checkIfWin(sum, threshold);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnWinningCrazyNumbersFirstRoundResult() {
        //given
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        List<Integer> listOfNumbers = List.of(7, 4, 10, 3, 8, 5, 3, 6, 0, 5);

        //when
        CrazyNumbersFirstRoundResult result = crazyNumbers.evaluate(listOfNumbers);

        //then
        assertThat(result.drawnNumbers()).containsExactlyInAnyOrderElementsOf(listOfNumbers);
        assertThat(result.isWin()).isTrue();
        assertThat(result.sum()).isEqualTo(51);
    }

    @Test
    void shouldReturnLosingCrazyNumbersFirstRoundResult() {
        //given
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        List<Integer> listOfNumbers = List.of(6, 4, 10, 3, 8, 5, 3, 6, 0, 5);

        //when
        CrazyNumbersFirstRoundResult result = crazyNumbers.evaluate(listOfNumbers);

        //then
        assertThat(result.drawnNumbers()).containsExactlyInAnyOrderElementsOf(listOfNumbers);
        assertThat(result.isWin()).isFalse();
        assertThat(result.sum()).isEqualTo(50);
    }
}