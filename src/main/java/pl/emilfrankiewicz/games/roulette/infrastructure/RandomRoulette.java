package pl.emilfrankiewicz.games.roulette.infrastructure;

import pl.emilfrankiewicz.games.roulette.domain.Roulette;
import pl.emilfrankiewicz.games.roulette.domain.RouletteResult;

import java.util.SplittableRandom;

public class RandomRoulette implements Roulette {

    public RouletteResult spin() {
        SplittableRandom number = new SplittableRandom();
        return new RouletteResult(number.nextInt(0, 37));
    }
}