package pl.emilfrankiewicz.games.roulette.domain;

public class BetOnEven implements Bet {

    @Override
    public boolean checkIfWin(RouletteResult rouletteResult) {
        return rouletteResult.isEven();
    }

    @Override
    public int payoutMultiplier() {
        return 1;
    }
}