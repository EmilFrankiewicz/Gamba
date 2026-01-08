package pl.emilfrankiewicz.games.slotmachine.domain;

enum WinCategory {

    VERY_HIGH,
    HIGH,
    MEDIUM,
    LOW,
    VERY_LOW,
    LOSS;

    public static WinCategory fromPayout(int payout) {
        return switch (payout) {
            case 100 -> VERY_HIGH;
            case 50 -> HIGH;
            case 20 -> MEDIUM;
            case 10 -> LOW;
            case 5 -> VERY_LOW;
            case 0 -> LOSS;
            default -> throw new IllegalStateException("Unexpected value: " + payout);
        };
    }
}
