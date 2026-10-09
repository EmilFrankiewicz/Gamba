package pl.emilfrankiewicz.games.crazynumbers.infrastructure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumbersGeneratorTest {

    @Test
    void shouldGenerate10NumbersFromRange0To10() {
        //given
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

        //when
        List<Integer> randomNumbers = randomNumbersGenerator.generate();

        //then
        assertThat(randomNumbers).hasSize(10);
        assertThat(randomNumbers).allSatisfy(number -> assertThat(number).isBetween(0, 10));
    }
}