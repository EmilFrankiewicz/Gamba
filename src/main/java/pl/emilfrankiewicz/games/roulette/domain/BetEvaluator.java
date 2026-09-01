package pl.emilfrankiewicz.games.roulette.domain;

import pl.emilfrankiewicz.game.domain.Amount;

public class BetEvaluator {

   public BetResult evaluate(PlacedBet placedBet, RouletteResult rouletteResult, PayoutCalculator payoutCalculator) {
        boolean isWin = placedBet.bet().checkIfWin(rouletteResult);
        if (!isWin) {
            return new BetResult(isWin, new Amount(0), placedBet, rouletteResult);
        }
        Amount winAmount = payoutCalculator.calculatePayout(placedBet.amount(), placedBet.bet());
        return new BetResult(isWin, winAmount, placedBet, rouletteResult);
    }
}