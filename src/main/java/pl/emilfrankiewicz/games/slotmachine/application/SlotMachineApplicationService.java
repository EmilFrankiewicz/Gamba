package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.game.domain.Amount;
import pl.emilfrankiewicz.game.domain.PreparedGame;
import pl.emilfrankiewicz.games.slotmachine.domain.Bet;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotDetailsFactory;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotGameOutcome;


public class SlotMachineApplicationService {

    private final SlotMachineService slotMachineService;

    public SlotMachineApplicationService(SlotMachineService slotMachineService) {
        this.slotMachineService = slotMachineService;
    }

    public PreparedGame prepare(Amount baseCost, Bet bet) {

        Amount cost = bet.calculateCost(baseCost);

        return new PreparedGame(
                cost,
                () -> playInternal(bet)
        );
    }

    private GameOutcome playInternal(Bet bet) {
        GameResult gameResult = slotMachineService.gameResult();
        String detailsJson = SlotDetailsFactory.jsonFrom(gameResult);

        SlotGameOutcome slotGameOutcome =
                SlotGameOutcome.from(
                        gameResult,
                        bet,
                        GameType.SLOT,
                        detailsJson
                );

        return SlotMachineOutcomeMapper
                .mapFromSlotGameOutcomeToGameOutcome(slotGameOutcome);
    }
}
