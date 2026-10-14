package pl.emilfrankiewicz.games.crazynumbers.domain;

import java.util.List;

public class CrazyNumbers {

    private static final int FIRST_ROUND_THRESHOLD = 50;

    int sum(List<Integer> listOfNumbers) {
        return listOfNumbers.stream().mapToInt(number -> number).sum();
    }

    boolean checkIfWin(int sum, int threshold) {
        return sum > threshold;
    }

    public CrazyNumbersFirstRoundResult evaluate(List<Integer> drawnNumbers) {
        int sumOfNumbers = sum(drawnNumbers);
        boolean isWin = checkIfWin(sumOfNumbers, FIRST_ROUND_THRESHOLD);
        return new CrazyNumbersFirstRoundResult(isWin, drawnNumbers, sumOfNumbers);
    }
}