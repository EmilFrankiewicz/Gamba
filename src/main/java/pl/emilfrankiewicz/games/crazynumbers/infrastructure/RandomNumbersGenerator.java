package pl.emilfrankiewicz.games.crazynumbers.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class RandomNumbersGenerator {

    public List<Integer> generate() {
        SplittableRandom splittableRandom = new SplittableRandom();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(splittableRandom.nextInt(0, 11));
        }
        return list;
    }
}
