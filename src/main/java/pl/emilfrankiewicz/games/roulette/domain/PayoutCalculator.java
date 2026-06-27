package pl.emilfrankiewicz.games.roulette.domain;

public class PayoutCalculator {
    Amount calculatePayout(Amount amount, Bet bet) {
        return new Amount(amount.getAmount() * bet.payoutMultiplier());
    }
}