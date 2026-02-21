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
}


