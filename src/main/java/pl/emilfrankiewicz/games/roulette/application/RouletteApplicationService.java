package pl.emilfrankiewicz.games.roulette.application;

import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.PreparedGame;
import pl.emilfrankiewicz.games.roulette.domain.*;

import static pl.emilfrankiewicz.games.roulette.application.RouletteMapper.mapFromBetResultToGameOutcome;

public class RouletteApplicationService {

    private final Roulette roulette;
    private final PayoutCalculator payoutCalculator;
    private final BetEvaluator betEvaluator;

    public RouletteApplicationService(Roulette roulette, PayoutCalculator payoutCalculator, BetEvaluator betEvaluator) {
        this.roulette = roulette;
        this.payoutCalculator = payoutCalculator;
        this.betEvaluator = betEvaluator;
    }

    public PreparedGame prepare(PlacedBet placedBet) {
        Amount cost = placedBet.amount();
        return new PreparedGame(cost, () -> playInternal(placedBet));
    }

    private GameOutcome playInternal(PlacedBet placedBet) {
        RouletteResult rouletteResult = roulette.spin();
        BetResult betResult = betEvaluator.evaluate(placedBet, rouletteResult, payoutCalculator);
        return mapFromBetResultToGameOutcome(betResult);
    }
}