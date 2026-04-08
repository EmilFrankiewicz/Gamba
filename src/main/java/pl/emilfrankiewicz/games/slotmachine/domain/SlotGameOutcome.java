package pl.emilfrankiewicz.games.slotmachine.domain;

import pl.emilfrankiewicz.game.domain.Bet;
import pl.emilfrankiewicz.game.domain.GameType;

import java.time.Instant;

public record SlotGameOutcome(boolean win, Instant occurredAt, GameType gameType, String details, int basePayout,
                              int finalPayout) {

    public static SlotGameOutcome from(GameResult gameResult, Bet bet, GameType gameType, String detailsJson) {
        int basePayout = gameResult.evaluation().payout();
        int finalPayout = basePayout * bet.multiplier();
        return new SlotGameOutcome(gameResult.win(), gameResult.occurredAt(), gameType, detailsJson, basePayout, finalPayout);
    }
}

