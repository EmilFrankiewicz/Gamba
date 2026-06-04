package pl.emilfrankiewicz.games.roulette.domain;

import java.util.Set;

public enum Color {
    GREEN, RED, BLACK;

    private static final Set<Integer> SET_OF_RED = Set.of(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    public static Color colorOf(int number) {
        if (number == 0) {
            return Color.GREEN;
        } else if (Color.SET_OF_RED.contains(number)) {
            return Color.RED;
        }
        return Color.BLACK;
    }
}
