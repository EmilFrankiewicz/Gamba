package pl.emilfrankiewicz.games.crazynumbers.domain;

public enum CrazyNumbersBet {

    TEN(10),
    HUNDRED(100),
    THOUSAND(1000),
    TEN_THOUSAND(10000);

    private final int value;

    CrazyNumbersBet(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}