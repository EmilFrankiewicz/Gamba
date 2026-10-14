package pl.emilfrankiewicz.games.crazynumbers.domain;

import java.util.List;

public record CrazyNumbersFirstRoundResult(boolean isWin, List<Integer> drawnNumbers, int sum) {
}
