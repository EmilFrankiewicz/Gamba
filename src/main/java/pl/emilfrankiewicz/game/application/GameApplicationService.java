package pl.emilfrankiewicz.game.application;

import pl.emilfrankiewicz.game.domain.GamePayout;
import pl.emilfrankiewicz.player.application.PlayerService;
import pl.emilfrankiewicz.player.domain.PlayerId;

public class GameApplicationService {

    private final PlayerService playerService;

    public GameApplicationService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void applyResult(PlayerId id, GamePayout payout) {
        int amount = payout.amount();
        if (amount > 0) {
            playerService.increaseBalance(id, amount);
        } else if (amount < 0) {
            playerService.decreaseBalance(id, Math.abs(amount));
        }
    }
}


