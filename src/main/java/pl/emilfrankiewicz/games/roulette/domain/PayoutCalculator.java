package pl.emilfrankiewicz.games.roulette.domain;

public class PayoutCalculator {
    int calculatePayout(int amount, Bet bet) {
        return amount * bet.payoutMultiplier();
    }
}