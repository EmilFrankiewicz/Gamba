package pl.emilfrankiewicz.games.roulette.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.roulette.domain.*;

import static org.assertj.core.api.Assertions.assertThat;

class RandomRouletteApplicationServiceTest {

    @Test
    void shouldReturnWinningGameOutcomeWhenPlayerWins() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        Roulette roulette = new FakeRoulette(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        //when
        GameOutcome gameOutcome = rouletteApplicationService.play(placedBet);

        //then
        assertThat(gameOutcome.win()).isTrue();
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameOutcome.basePayout()).isEqualTo(350);
        assertThat(gameOutcome.payout()).isEqualTo(360);
        assertThat(gameOutcome.details()).isNotBlank();
        assertThat(gameOutcome.occurredAt()).isNotNull();
    }

    @Test
    void shouldReturnLosingGameOutcomeWhenPlayerLoses() {
        //given
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        Roulette roulette = new FakeRoulette(2);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        //when
        GameOutcome gameOutcome = rouletteApplicationService.play(placedBet);

        //then
        assertThat(gameOutcome.win()).isFalse();
        assertThat(gameOutcome.gameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameOutcome.basePayout()).isEqualTo(0);
        assertThat(gameOutcome.payout()).isEqualTo(0);
        assertThat(gameOutcome.details()).isNotBlank();
        assertThat(gameOutcome.occurredAt()).isNotNull();
    }
}