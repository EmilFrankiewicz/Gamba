package pl.emilfrankiewicz.game.domain;

public enum Bet {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private final int multiplier;

    Bet(int multiplier) {
        this.multiplier = multiplier;
    }

    public int multiplier() {
        return multiplier;
    }

    public static Bet fromString(String betToParse) {
        if (betToParse == null) {
            throw new InvalidBetException("Bet cannot be null");
        }

        String normalized = betToParse.trim().toUpperCase();

        try {
            return Bet.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new InvalidBetException("Invalid bet value: " + betToParse);
        }
    }
}

