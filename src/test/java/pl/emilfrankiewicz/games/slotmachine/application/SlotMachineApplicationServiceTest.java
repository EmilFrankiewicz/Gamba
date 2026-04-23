package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.Bet;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.slotmachine.domain.EvaluationResult;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.BAR;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.SEVEN;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.LOSS;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.VERY_HIGH;

class SlotMachineApplicationServiceTest {

    @Test
    void shouldReturnGameOutcomeWhenPlayingSlotMachine() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(
                true,
                List.of(SEVEN, SEVEN, SEVEN),
                new EvaluationResult(100, VERY_HIGH),
                now
        );
        Bet bet = Bet.ONE;

        when(slotMachineService.gameResult()).thenReturn(gameResult);

        GameOutcome outcome = slotMachineApplicationService.play(bet);

        assertThat(outcome.win()).isEqualTo(true);
        assertThat(outcome.payout()).isEqualTo(100);
        assertThat(outcome.occurredAt()).isEqualTo(now);
        assertThat(outcome.gameType()).isEqualTo(GameType.SLOT);
        assertThat(outcome.details()).contains("SEVEN");
        assertThat(outcome.details()).contains("VERY_HIGH");

        verify(slotMachineService).gameResult();
    }

    @Test
    void shouldReturnGameOutcomeWhenPlayerLoses() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(
                false,
                List.of(SEVEN, SEVEN, BAR),
                new EvaluationResult(0, LOSS),
                now
        );
        Bet bet = Bet.ONE;

        when(slotMachineService.gameResult()).thenReturn(gameResult);

        GameOutcome outcome = slotMachineApplicationService.play(bet);

        assertThat(outcome.win()).isEqualTo(false);
        assertThat(outcome.payout()).isEqualTo(0);
        assertThat(outcome.occurredAt()).isEqualTo(now);
        assertThat(outcome.gameType()).isEqualTo(GameType.SLOT);
        assertThat(outcome.details()).contains("BAR");
        assertThat(outcome.details()).contains("LOSS");

        verify(slotMachineService).gameResult();
    }

    @Test
    void shouldThrowExceptionWhenGameResultFails() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);
        Bet bet = Bet.ONE;

        when(slotMachineService.gameResult()).thenThrow(new RuntimeException("Failed to generate game result"));

        Throwable thrown = catchThrowable(() -> slotMachineApplicationService.play(bet));

        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Failed to generate game result");

        verify(slotMachineService).gameResult();
    }
}
