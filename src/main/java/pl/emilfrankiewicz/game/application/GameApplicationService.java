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

    public GameOutcome playGame(PlayerId id, PreparedGame preparedGame) {
        Player player = playerService.find(id);

        Amount balanceBefore = new Amount(player.getBalance().getAmount());
        Amount cost = preparedGame.cost();

        Player afterPay = player.payForGame(cost);

        GameOutcome outcome = preparedGame.play();

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
                new Amount(finalPlayer.getBalance().getAmount())
        );

        gameHistoryRepository.save(entry);

        return outcome;
    }
}


