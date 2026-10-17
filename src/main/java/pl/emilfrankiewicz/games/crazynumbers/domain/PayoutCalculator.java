package pl.emilfrankiewicz.games.crazynumbers.domain;

import pl.emilfrankiewicz.game.domain.Amount;

public class PayoutCalculator {

    public Amount calculatePayout(CrazyNumbersBet bet, boolean isWin) {
        return isWin ? new Amount(bet.getValue() * 2) : new Amount(0);
    }
}