package pl.emilfrankiewicz.games.roulette.domain;

public class BetResultDetailsFactory {

    public static String jsonFrom(BetResult betResult) {

        return "{"
                + "\"isWin\": " + betResult.isWin() + ","
                + "\"winAmount\": " + betResult.winAmount().getAmount() + ","
                + "\"betAmount\": " + betResult.placedBet().amount().getAmount() + ","
                + "\"payout\": " + betResult.payout().getAmount() + ","
                + "\"betType\": \"" + betResult.placedBet().bet().getClass().getSimpleName() + "\","
                + "\"rouletteResult\": {"
                + "\"number\": " + betResult.rouletteResult().getNumber() + ","
                + "\"color\": \"" + betResult.rouletteResult().getColor().name() + "\""
                + "},"
                + "\"occurredAt\": \"" + betResult.occurredAt() + "\""
                + "}";
    }
}