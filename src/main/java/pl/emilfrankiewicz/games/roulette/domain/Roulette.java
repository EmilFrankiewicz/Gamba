package pl.emilfrankiewicz.games.roulette.domain;

import java.util.SplittableRandom;

public class Roulette {
    public int spin() {
        SplittableRandom number = new SplittableRandom();
        return number.nextInt(0, 37);
    }
}