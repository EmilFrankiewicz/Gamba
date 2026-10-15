package pl.emilfrankiewicz.games.crazynumbers.application;

import pl.emilfrankiewicz.games.crazynumbers.domain.CrazyNumbers;
import pl.emilfrankiewicz.games.crazynumbers.domain.CrazyNumbersFirstRoundResult;
import pl.emilfrankiewicz.games.crazynumbers.domain.FirstRoundGameResult;
import pl.emilfrankiewicz.games.crazynumbers.domain.NumberGenerator;

import java.time.Instant;
import java.util.List;

public class CrazyNumbersService {

    private final NumberGenerator numberGenerator;
    private final CrazyNumbers crazyNumbers;

    public CrazyNumbersService(NumberGenerator numberGenerator, CrazyNumbers crazyNumbers) {
        this.numberGenerator = numberGenerator;
        this.crazyNumbers = crazyNumbers;
    }

    public FirstRoundGameResult generateResult() {
        List<Integer> randomNumbersList = numberGenerator.generate();
        CrazyNumbersFirstRoundResult result = crazyNumbers.evaluate(randomNumbersList);
        return new FirstRoundGameResult(result, Instant.now());
    }
}