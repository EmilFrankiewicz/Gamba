package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.game.application.GameApplicationService;
import pl.emilfrankiewicz.games.slotmachine.domain.GameResult;
import pl.emilfrankiewicz.player.domain.PlayerId;

public class SlotMachineApplicationService {

    private final SlotMachineService slotMachineService;
    private final GameApplicationService gameApplicationService;

    public SlotMachineApplicationService(SlotMachineService slotMachineService, GameApplicationService gameApplicationService) {
        this.slotMachineService = slotMachineService;
        this.gameApplicationService = gameApplicationService;
    }

    GameResult playSlotMachine(PlayerId id) {
        GameResult gameResult = slotMachineService.gameResult();
        gameApplicationService.applyResult(id, gameResult);
        return gameResult;
    }
}
