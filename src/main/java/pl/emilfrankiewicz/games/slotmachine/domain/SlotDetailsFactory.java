package pl.emilfrankiewicz.games.slotmachine.domain;

import java.util.stream.Collectors;

public class SlotDetailsFactory {

    public static String jsonFrom(GameResult result) {

        String symbolsJson = result.symbols().stream()
                .map(symbol -> "\"" + symbol.name() + "\"")
                .collect(Collectors.joining(","));

        return "{"
                + "\"symbols\": [" + symbolsJson + "],"
                + "\"winCategory\": \"" + result.evaluation().winCategory().name() + "\","
                + "\"payout\": " + result.evaluation().payout()
                + "}";
    }
}
