package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotGameOutcome;

public class SlotMachineOutcomeMapper {

    public static GameOutcome mapFromSlotGameOutcomeToGameOutcome(SlotGameOutcome slotGameOutcome)
    {
        return new GameOutcome(slotGameOutcome.win(), slotGameOutcome.occurredAt(), slotGameOutcome.gameType(), slotGameOutcome.details(), slotGameOutcome.basePayout(), slotGameOutcome.payout());
    }
}
