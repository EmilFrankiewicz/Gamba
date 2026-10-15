package pl.emilfrankiewicz.games.crazynumbers.application;

import pl.emilfrankiewicz.games.crazynumbers.domain.NumberGenerator;

import java.util.List;

public class FakeNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate() {
        return List.of(7, 4, 10, 3, 8, 5, 3, 6, 0, 5);
    }
}