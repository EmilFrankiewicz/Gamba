package pl.emilfrankiewicz.game.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.*;
import pl.emilfrankiewicz.game.history.domain.GameHistoryRepository;
import pl.emilfrankiewicz.game.history.infrastructure.InMemoryGameHistoryRepository;
import pl.emilfrankiewicz.player.application.InMemoryPlayerRepository;
import pl.emilfrankiewicz.player.application.PlayerService;
import pl.emilfrankiewicz.player.domain.Balance;
import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class GameApplicationServiceTest {

    @Test
    void shouldPlayGameAndUpdatePlayerBalanceAndSaveHistory() {
        // given
        PlayerRepository playerRepo = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(playerRepo);


        PlayerId playerId = new PlayerId("1");
        playerRepo.save(new Player(playerId, new Balance(100)));

        GameCostPolicy costPolicy = new StandardGameCostPolicy(10);

        GameHistoryRepository historyRepo = new InMemoryGameHistoryRepository();

        GameEngine fakeEngine = bet -> new GameOutcome(
                true,
                Instant.now(),
                GameType.SLOT,
                "details",
                50,
                100
        );

        GameApplicationService service = new GameApplicationService(
                playerService,
                fakeEngine,
                costPolicy,
                historyRepo
        );

        // when
        GameOutcome result = service.playGame(playerId, Bet.ONE);

        // then
        Player updated = playerRepo.find(playerId);

        assertThat(updated.getBalance().getAmount()).isEqualTo(190);
        assertThat(result.payout()).isEqualTo(100);
    }
}