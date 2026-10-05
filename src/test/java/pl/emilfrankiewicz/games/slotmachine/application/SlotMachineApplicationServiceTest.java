package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.PreparedGame;
import pl.emilfrankiewicz.games.slotmachine.domain.Bet;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.slotmachine.domain.EvaluationResult;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static pl.emilfrankiewicz.games.slotmachine.domain.Symbol.*;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.LOSS;
import static pl.emilfrankiewicz.games.slotmachine.domain.WinCategory.VERY_HIGH;

class SlotMachineApplicationServiceTest {

    @Test
    void shouldReturnCost100WhenAmountIs20AndBetIsFIVE() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);

        PreparedGame preparedGame = slotMachineApplicationService.prepare(new Amount(20), Bet.FIVE);

        assertThat(preparedGame.cost()).isEqualTo(new Amount(100));
    }

    @Test
    void shouldNotRunGameWhenPreparing() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);

        PreparedGame preparedGame = slotMachineApplicationService.prepare(new Amount(20), Bet.FIVE);

        verify(slotMachineService, never()).gameResult();
    }

    @Test
    void shouldReturnWinningGameOutcomeWhenPreparedGameIsPlayed() {
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

        PreparedGame preparedGame = slotMachineApplicationService.prepare(new Amount(20), Bet.FIVE);

        verify(slotMachineService, never()).gameResult();


        when(slotMachineService.gameResult()).thenReturn(gameResult);


        GameOutcome gameOutcome = preparedGame.play();

        verify(slotMachineService).gameResult();


        assertThat(gameOutcome.win()).isTrue();
        assertThat(gameOutcome.payout()).isEqualTo(new Amount(500));
        assertThat(gameOutcome.occurredAt()).isEqualTo(now);
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.SLOT);
        assertThat(gameOutcome.details()).contains("SEVEN");
        assertThat(gameOutcome.details()).contains("VERY_HIGH");
    }

    @Test
    void shouldReturnLosingGameOutcomeWhenPreparedGameIsPlayed() {
        SlotMachineService slotMachineService = mock(SlotMachineService.class);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);

        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        GameResult gameResult = new GameResult(
                false,
                List.of(SEVEN, SEVEN, CHERRY),
                new EvaluationResult(0, LOSS),
                now
        );

        PreparedGame preparedGame = slotMachineApplicationService.prepare(new Amount(20), Bet.FIVE);

        verify(slotMachineService, never()).gameResult();


        when(slotMachineService.gameResult()).thenReturn(gameResult);


        GameOutcome gameOutcome = preparedGame.play();

        verify(slotMachineService).gameResult();


        assertThat(gameOutcome.win()).isFalse();
        assertThat(gameOutcome.payout()).isEqualTo(new Amount(0));
        assertThat(gameOutcome.occurredAt()).isEqualTo(now);
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.SLOT);
        assertThat(gameOutcome.details()).contains("CHERRY");
        assertThat(gameOutcome.details()).contains("LOSS");
    }
}