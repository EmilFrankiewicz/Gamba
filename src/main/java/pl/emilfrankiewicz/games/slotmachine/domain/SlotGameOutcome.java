package pl.emilfrankiewicz.games.slotmachine.domain;

import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.GameType;

import java.time.Instant;

public record SlotGameOutcome(boolean win, Instant occurredAt, GameType gameType, String details, Amount basePayout,
                              Amount payout) {

    public static SlotGameOutcome from(GameResult gameResult, Bet bet, GameType gameType, String detailsJson) {
        int basePayout = gameResult.evaluation().payout();
        int finalPayout = basePayout * bet.multiplier();
        return new SlotGameOutcome(gameResult.win(), gameResult.occurredAt(), gameType, detailsJson, new Amount(basePayout), new Amount(finalPayout));
    }
}