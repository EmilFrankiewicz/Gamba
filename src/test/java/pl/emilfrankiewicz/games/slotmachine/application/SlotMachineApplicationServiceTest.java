package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.application.GameApplicationService;
import pl.emilfrankiewicz.games.slotmachine.domain.EvaluationResult;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.BAR;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.SEVEN;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.LOSS;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.VERY_HIGH;

class SlotMachineApplicationServiceTest {

    @Test
    void shouldReturnGameResultWhenPlayingSlotMachine() {
        // given
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        GameApplicationService gameApplicationService = mock(GameApplicationService.class);
        SlotMachineApplicationService slotMachineApplicationService = new SlotMachineApplicationService(slotMachineService, gameApplicationService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(true, List.of(SEVEN, SEVEN, SEVEN), new EvaluationResult(100, VERY_HIGH), now);

        when(slotMachineService.gameResult()).thenReturn(gameResult);

        PlayerId id = new PlayerId("1");

        // when
        GameResult result = slotMachineApplicationService.playSlotMachine(id);

        // then
        assertThat(result).isEqualTo(gameResult);
        verify(slotMachineService).gameResult();
        verify(gameApplicationService).applyResult(id, gameResult);
    }

    @Test
    void shouldReturnGameResultWhenPlayerLoses() {
        // given
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        GameApplicationService gameApplicationService = mock(GameApplicationService.class);
        SlotMachineApplicationService slotMachineApplicationService = new SlotMachineApplicationService(slotMachineService, gameApplicationService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(false, List.of(SEVEN, SEVEN, BAR), new EvaluationResult(0, LOSS), now);

        when(slotMachineService.gameResult()).thenReturn(gameResult);

        PlayerId id = new PlayerId("1");

        // when
        GameResult result = slotMachineApplicationService.playSlotMachine(id);

        // then
        assertThat(result).isEqualTo(gameResult);
        verify(slotMachineService).gameResult();
        verify(gameApplicationService).applyResult(id, gameResult);
    }

    @Test
    void shouldThrowExceptionWhenGameResultFails() {
        // given
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        GameApplicationService gameApplicationService = mock(GameApplicationService.class);
        SlotMachineApplicationService slotMachineApplicationService = new SlotMachineApplicationService(slotMachineService, gameApplicationService);

        when(slotMachineService.gameResult()).thenThrow(new RuntimeException("Failed to generate game result"));

        PlayerId id = new PlayerId("1");

        // when
        Throwable thrown = catchThrowable(() -> slotMachineApplicationService.playSlotMachine(id));

        // then
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Failed to generate game result");
        verify(slotMachineService).gameResult();
        verify(gameApplicationService, never()).applyResult(any(), any());
    }

    @Test
    void shouldThrowExceptionWhenApplyResultFails() {
        // given
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        GameApplicationService gameApplicationService = mock(GameApplicationService.class);
        SlotMachineApplicationService slotMachineApplicationService = new SlotMachineApplicationService(slotMachineService, gameApplicationService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(false, List.of(SEVEN, SEVEN, BAR), new EvaluationResult(0, LOSS), now);

        PlayerId id = new PlayerId("1");

        when(slotMachineService.gameResult()).thenReturn(gameResult);

        doThrow(new RuntimeException("Failed to generate game result")).when(gameApplicationService).applyResult(id, gameResult);

        // when
        Throwable thrown = catchThrowable(() -> slotMachineApplicationService.playSlotMachine(id));

        // then
        assertThat(thrown).isInstanceOf(RuntimeException.class).hasMessage("Failed to generate game result");

        verify(slotMachineService).gameResult();
        verify(gameApplicationService).applyResult(id, gameResult);
    }

}