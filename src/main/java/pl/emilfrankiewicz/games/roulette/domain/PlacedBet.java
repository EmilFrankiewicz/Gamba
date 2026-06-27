package pl.emilfrankiewicz.games.roulette.domain;

public record PlacedBet(Amount amount, Bet bet) {
    public PlacedBet {
        if (amount.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount cannot be less than or equal to 0");
        }
    }
}
