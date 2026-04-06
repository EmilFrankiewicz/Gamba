package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotGameOutcome;

public class SlotMachineOutcomeMapper {

    public static SlotGameOutcome mapFromGameResultToSlotGameOutcome(GameResult gameResult, GameType gameType, String detailsJson) {
        return new SlotGameOutcome(gameResult.win(), gameResult.occurredAt(), gameType, detailsJson, gameResult.evaluation().payout());
    }

    public static GameOutcome mapFromSlotGameOutcomeToGameOutcome(SlotGameOutcome slotGameOutcome)
    {
        return new GameOutcome(slotGameOutcome.win(), slotGameOutcome.occurredAt(), slotGameOutcome.gameType(), slotGameOutcome.details(), slotGameOutcome.payout());
    }
}
