package pl.emilfrankiewicz.games.roulette.domain;

import pl.emilfrankiewicz.game.domain.Amount;

public class PayoutCalculator {
    Amount calculatePayout(Amount amount, Bet bet) {
        return new Amount(amount.getAmount() * bet.payoutMultiplier());
    }
}