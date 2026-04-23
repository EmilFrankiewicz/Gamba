package pl.emilfrankiewicz.game.application;

import pl.emilfrankiewicz.game.domain.*;
import pl.emilfrankiewicz.game.history.domain.GameHistoryEntry;
import pl.emilfrankiewicz.game.history.domain.GameHistoryId;
import pl.emilfrankiewicz.game.history.domain.GameHistoryRepository;
import pl.emilfrankiewicz.player.application.PlayerService;
import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;

import java.util.UUID;

public class GameApplicationService {

    private final PlayerService playerService;
    private final GameEngine gameEngine;
    private final GameCostPolicy gameCostPolicy;
    private final GameHistoryRepository gameHistoryRepository;

    public GameApplicationService(PlayerService playerService, GameEngine gameEngine, GameCostPolicy gameCostPolicy, GameHistoryRepository gameHistoryRepository) {
        this.playerService = playerService;
        this.gameEngine = gameEngine;
        this.gameCostPolicy = gameCostPolicy;
        this.gameHistoryRepository = gameHistoryRepository;
    }

    public GameOutcome playGame(PlayerId id, Bet bet) {
        Player player = playerService.find(id);
        int balanceBefore = player.getBalance().getAmount();
        int cost = gameCostPolicy.calculateCost(bet);

        Player afterPay = player.payForGame(cost);

        GameOutcome outcome = gameEngine.play(bet);

        Player finalPlayer = playerService.save(afterPay.applyPayout(outcome.payout()));

        GameHistoryEntry entry = new GameHistoryEntry(
                new GameHistoryId(UUID.randomUUID().toString()),
                id,
                outcome.occurredAt(),
                outcome.gameType(),
                outcome.details(),
                cost,
                outcome.payout(),
                balanceBefore,
                finalPlayer.getBalance().getAmount()
        );

        gameHistoryRepository.save(entry);

        return outcome;
    }
}


