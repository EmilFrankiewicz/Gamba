package pl.emilfrankiewicz.games.roulette.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.game.domain.PreparedGame;
import pl.emilfrankiewicz.games.roulette.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RouletteApplicationServiceTest {

    @Test
    void shouldReturnCostAndNotRunGameWhenPreparing() {
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        BetEvaluator betEvaluator = new BetEvaluator();
        Roulette roulette = mock(Roulette.class);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);
        verify(roulette, never()).spin();

        assertThat(preparedGame.cost()).isEqualTo(new Amount(10));
    }

    @Test
    void shouldReturnWinningGameOutcomeWhenPreparedGameIsPlayed() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        Roulette roulette = new FakeRoulette(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        //when
        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);
        GameOutcome gameOutcome = preparedGame.play();

        //then
        assertThat(gameOutcome.win()).isTrue();
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameOutcome.basePayout()).isEqualTo(new Amount(350));
        assertThat(gameOutcome.payout()).isEqualTo(new Amount(360));
        assertThat(gameOutcome.details()).contains("\"isWin\": true");
        assertThat(gameOutcome.occurredAt()).isNotNull();
    }

    @Test
    void shouldReturnLosingGameOutcomeWhenPreparedGameIsPlayed() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        Roulette roulette = new FakeRoulette(2);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        //when
        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);
        GameOutcome gameOutcome = preparedGame.play();

        //then
        assertThat(gameOutcome.win()).isFalse();
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameOutcome.basePayout()).isEqualTo(new Amount(0));
        assertThat(gameOutcome.payout()).isEqualTo(new Amount(0));
        assertThat(gameOutcome.details()).contains("\"isWin\": false");
        assertThat(gameOutcome.occurredAt()).isNotNull();
    }
}