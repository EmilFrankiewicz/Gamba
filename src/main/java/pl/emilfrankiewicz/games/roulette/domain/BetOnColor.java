package pl.emilfrankiewicz.games.roulette.domain;

public class BetOnColor implements Bet{

    private final Color color;

    public BetOnColor(Color color) {
        this.color = color;
    }

    public boolean checkIfWin(RouletteResult rouletteResult) {
        return this.color == rouletteResult.getColor();
    }
}
