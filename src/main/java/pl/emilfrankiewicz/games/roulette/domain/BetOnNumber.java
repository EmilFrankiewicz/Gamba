package pl.emilfrankiewicz.games.roulette.domain;

public class BetOnNumber implements Bet {

    private final int number;

    public BetOnNumber(int number) {
        if (number <= -1 || number >= 37) {
            throw new IllegalArgumentException("Not in range 0-37");
        }
        this.number = number;
    }

    public boolean checkIfWin(RouletteResult rouletteResult) {
        return this.number == rouletteResult.getNumber();
    }

    @Override
    public int payoutMultiplier() {
        return 35;
    }
}
