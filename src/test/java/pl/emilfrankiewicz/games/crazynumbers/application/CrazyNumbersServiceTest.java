package pl.emilfrankiewicz.games.crazynumbers.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.games.crazynumbers.domain.CrazyNumbers;
import pl.emilfrankiewicz.games.crazynumbers.domain.FirstRoundGameResult;
import pl.emilfrankiewicz.games.crazynumbers.domain.NumberGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CrazyNumbersServiceTest {

    @Test
    void shouldGenerateFirstRoundGameResult() {
        //given
        NumberGenerator fakeNumberGenerator = new FakeNumberGenerator();
        CrazyNumbers crazyNumbers = new CrazyNumbers();
        CrazyNumbersService crazyNumbersService = new CrazyNumbersService(fakeNumberGenerator, crazyNumbers);
        List<Integer> expectedNumbers = List.of(7, 4, 10, 3, 8, 5, 3, 6, 0, 5);
        //when
        FirstRoundGameResult firstRoundGameResult = crazyNumbersService.generateResult();

        //then
        assertThat(firstRoundGameResult.crazyNumbersFirstRoundResult().isWin()).isTrue();
        assertThat(firstRoundGameResult.crazyNumbersFirstRoundResult().drawnNumbers()).containsExactlyInAnyOrderElementsOf(expectedNumbers);
        assertThat(firstRoundGameResult.crazyNumbersFirstRoundResult().sum()).isEqualTo(51);
        assertThat(firstRoundGameResult.occurredAt()).isNotNull();
    }
}