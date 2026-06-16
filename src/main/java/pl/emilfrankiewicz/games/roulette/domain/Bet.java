package pl.emilfrankiewicz.games.roulette.domain;

public interface Bet {
    boolean checkIfWin(RouletteResult rouletteResult);
}
