package pl.emilfrankiewicz.games.roulette.domain;

import java.util.SplittableRandom;

public class Roulette {

    public RouletteResult spin() {
        SplittableRandom number = new SplittableRandom();
        return new RouletteResult(number.nextInt(0, 37));
    }
}