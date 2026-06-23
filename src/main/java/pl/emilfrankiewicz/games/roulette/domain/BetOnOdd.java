package pl.emilfrankiewicz.games.roulette.domain;

public class BetOnOdd implements Bet {

    @Override
    public boolean checkIfWin(RouletteResult rouletteResult) {
        return rouletteResult.isOdd();
    }

    @Override
    public int payoutMultiplier() {
        return 1;
    }
}