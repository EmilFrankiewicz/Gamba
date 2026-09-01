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
    private final GameHistoryRepository gameHistoryRepository;

    public GameApplicationService(PlayerService playerService, GameHistoryRepository gameHistoryRepository) {
        this.playerService = playerService;
        this.gameHistoryRepository = gameHistoryRepository;
    }

    public GameOutcome playGame(PlayerId id, PlayedGame playedGame) {
        Player player = playerService.find(id);
        int balanceBefore = player.getBalance().getAmount();

        int cost = playedGame.cost().getAmount();

        Player afterPay = player.payForGame(cost);

        Player finalPlayer = playerService.save(afterPay.applyPayout(playedGame.gameOutcome().payout()));

        GameHistoryEntry entry = new GameHistoryEntry(
                new GameHistoryId(UUID.randomUUID().toString()),
                id,
                playedGame.gameOutcome().
                        occurredAt(),
                playedGame.gameOutcome().gameType(),
                playedGame.gameOutcome().details(),
                cost,
                playedGame.gameOutcome().payout(),
                balanceBefore,
                finalPlayer.getBalance().getAmount()
        );

        gameHistoryRepository.save(entry);

        return playedGame.gameOutcome();
    }
}


