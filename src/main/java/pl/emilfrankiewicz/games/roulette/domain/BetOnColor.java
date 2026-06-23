package pl.emilfrankiewicz.games.roulette.domain;

public class BetOnColor implements Bet {

    private final Color color;

    public BetOnColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null");
        }
        if (color == Color.GREEN) {
            throw new IllegalArgumentException("Only RED or BLACK colors is allowed");
        }
        this.color = color;
    }

    public boolean checkIfWin(RouletteResult rouletteResult) {
        return this.color == rouletteResult.getColor();
    }

    @Override
    public int payoutMultiplier() {
        return 1;
    }
}