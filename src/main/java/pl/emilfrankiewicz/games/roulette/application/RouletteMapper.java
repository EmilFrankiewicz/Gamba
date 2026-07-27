package pl.emilfrankiewicz.games.roulette.application;

import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.roulette.domain.BetResult;
import pl.emilfrankiewicz.games.roulette.domain.BetResultDetailsFactory;

public class RouletteMapper {

    public static GameOutcome mapFromBetResultToGameOutcome(BetResult betResult) {
        return new GameOutcome(betResult.isWin(), betResult.occurredAt(), GameType.ROULETTE, BetResultDetailsFactory.jsonFrom(betResult), betResult.winAmount().getAmount(), betResult.payout().getAmount());
    }
}
