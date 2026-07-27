package pl.emilfrankiewicz.games.roulette.application;

import pl.emilfrankiewicz.games.roulette.domain.Roulette;
import pl.emilfrankiewicz.games.roulette.domain.RouletteResult;

public class FakeRoulette implements Roulette {

    private final int number;

    public FakeRoulette(int number) {
        this.number = number;
    }

    @Override
    public RouletteResult spin() {
        return new RouletteResult(number);
    }
}
