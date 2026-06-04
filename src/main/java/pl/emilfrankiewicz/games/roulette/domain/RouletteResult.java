package pl.emilfrankiewicz.games.roulette.domain;

public class RouletteResult {

    private final int number;

    public RouletteResult(int number) {
        if (number <= -1 || number >= 37) {
            throw new IllegalArgumentException("Not in range 0-37");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEven() {
        return number % 2 == 0;
    }

    public Color getColor() {
        return Color.colorOf(number);
    }
}
