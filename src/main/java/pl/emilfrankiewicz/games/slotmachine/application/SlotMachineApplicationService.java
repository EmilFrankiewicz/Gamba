package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.game.domain.Bet;
import pl.emilfrankiewicz.game.domain.GameEngine;
import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.GameType;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotDetailsFactory;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotGameOutcome;


public class SlotMachineApplicationService implements GameEngine {

    private final SlotMachineService slotMachineService;

    public SlotMachineApplicationService(SlotMachineService slotMachineService) {
        this.slotMachineService = slotMachineService;
    }

    public GameOutcome play(Bet bet) {
        GameResult gameResult = slotMachineService.gameResult();
        String detailsJson = SlotDetailsFactory.jsonFrom(gameResult);
        SlotGameOutcome gameOutcome = SlotGameOutcome.from(gameResult, bet, GameType.SLOT, detailsJson);
        return SlotMachineOutcomeMapper.mapFromSlotGameOutcomeToGameOutcome(gameOutcome);
    }
}
