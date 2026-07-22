package pl.emilfrankiewicz.games.roulette.domain;

public record PlacedBet(Amount amount, Bet bet) {
    public PlacedBet {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (bet == null) {
            throw new IllegalArgumentException("Bet cannot be null");
        }
        if (amount.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount cannot be less than or equal to 0");
        }
    }
}
