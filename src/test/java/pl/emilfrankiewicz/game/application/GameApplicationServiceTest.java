package pl.emilfrankiewicz.game.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.game.domain.*;
import pl.emilfrankiewicz.game.history.domain.GameHistoryEntry;
import pl.emilfrankiewicz.game.history.domain.GameHistoryRepository;
import pl.emilfrankiewicz.game.history.infrastructure.InMemoryGameHistoryRepository;
import pl.emilfrankiewicz.games.roulette.application.FakeRoulette;
import pl.emilfrankiewicz.games.roulette.application.RouletteApplicationService;
import pl.emilfrankiewicz.games.roulette.domain.*;
import pl.emilfrankiewicz.games.slotmachine.application.SlotMachineApplicationService;
import pl.emilfrankiewicz.games.slotmachine.application.SlotMachineService;
import pl.emilfrankiewicz.games.slotmachine.application.StubSymbolGenerator;
import pl.emilfrankiewicz.games.slotmachine.domain.*;
import pl.emilfrankiewicz.games.slotmachine.domain.Bet;
import pl.emilfrankiewicz.games.slotmachine.domain.SymbolGenerator;
import pl.emilfrankiewicz.player.application.InMemoryPlayerRepository;
import pl.emilfrankiewicz.player.application.PlayerService;
import pl.emilfrankiewicz.player.domain.Balance;
import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class GameApplicationServiceTest {

    @Test
    void shouldPlayRouletteGameAndUpdatePlayerBalanceAndSaveHistoryWhenWin() {
        //given
        Roulette roulette = new FakeRoulette(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        PlayerId playerId = new PlayerId("1");
        playerRepository.save(new Player(playerId, new Balance(100)));
        GameHistoryRepository gameHistoryRepository = new InMemoryGameHistoryRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        GameApplicationService gameApplicationService = new GameApplicationService(playerService, gameHistoryRepository);
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);

        //when
        GameOutcome result = gameApplicationService.playGame(playerId, preparedGame);

        //then
        Player updated = playerRepository.find(playerId);

        assertThat(updated.getBalance().getAmount()).isEqualTo(450);
        assertThat(result.payout()).isEqualTo(new Amount(360));

        List<GameHistoryEntry> gameHistoryEntries = gameHistoryRepository.findByPlayerId(playerId);
        assertThat(gameHistoryEntries).hasSize(1);

        GameHistoryEntry gameHistoryEntry = gameHistoryEntries.getFirst();
        assertThat(gameHistoryEntry.getPlayerId()).isEqualTo(new PlayerId("1"));
        assertThat(gameHistoryEntry.getOccurredAt()).isEqualTo(result.occurredAt());
        assertThat(gameHistoryEntry.getGameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameHistoryEntry.getDetails()).isEqualTo(result.details());
        assertThat(gameHistoryEntry.getCost()).isEqualTo(new Amount(10));
        assertThat(gameHistoryEntry.getPayout()).isEqualTo(new Amount(360));
        assertThat(gameHistoryEntry.getBalanceBefore()).isEqualTo(new Amount(100));
        assertThat(gameHistoryEntry.getBalanceAfter()).isEqualTo(new Amount(450));
    }

    @Test
    void shouldPlayRouletteGameAndUpdatePlayerBalanceAndSaveHistoryWhenLoss() {
        //given
        Roulette roulette = new FakeRoulette(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        PlayerId playerId = new PlayerId("1");
        playerRepository.save(new Player(playerId, new Balance(100)));
        GameHistoryRepository gameHistoryRepository = new InMemoryGameHistoryRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        GameApplicationService gameApplicationService = new GameApplicationService(playerService, gameHistoryRepository);
        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(2));
        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);

        //when
        GameOutcome result = gameApplicationService.playGame(playerId, preparedGame);

        //then
        Player updated = playerRepository.find(playerId);

        assertThat(updated.getBalance().getAmount()).isEqualTo(90);
        assertThat(result.payout()).isEqualTo(new Amount(0));

        List<GameHistoryEntry> gameHistoryEntries = gameHistoryRepository.findByPlayerId(playerId);
        assertThat(gameHistoryEntries).hasSize(1);

        GameHistoryEntry gameHistoryEntry = gameHistoryEntries.getFirst();
        assertThat(gameHistoryEntry.getPlayerId()).isEqualTo(new PlayerId("1"));
        assertThat(gameHistoryEntry.getOccurredAt()).isEqualTo(result.occurredAt());
        assertThat(gameHistoryEntry.getGameType()).isEqualTo(GameType.ROULETTE);
        assertThat(gameHistoryEntry.getDetails()).isEqualTo(result.details());
        assertThat(gameHistoryEntry.getCost()).isEqualTo(new Amount(10));
        assertThat(gameHistoryEntry.getPayout()).isEqualTo(new Amount(0));
        assertThat(gameHistoryEntry.getBalanceBefore()).isEqualTo(new Amount(100));
        assertThat(gameHistoryEntry.getBalanceAfter()).isEqualTo(new Amount(90));
    }

    @Test
    void shouldNotChargePlayerAndNotStartGameWhenBalanceIsInsufficient() {
        //given
        Roulette roulette = mock(Roulette.class);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        PlayerId playerId = new PlayerId("1");
        playerRepository.save(new Player(playerId, new Balance(100)));
        GameHistoryRepository gameHistoryRepository = new InMemoryGameHistoryRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        GameApplicationService gameApplicationService = new GameApplicationService(playerService, gameHistoryRepository);
        PlacedBet placedBet = new PlacedBet(new Amount(110), new BetOnNumber(1));
        PreparedGame preparedGame = rouletteApplicationService.prepare(placedBet);

        //when
        Throwable thrown = catchThrowable(() -> gameApplicationService.playGame(playerId, preparedGame));

        //then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount cannot be less than 0");

        Player updated = playerRepository.find(playerId);

        assertThat(updated.getBalance().getAmount()).isEqualTo(100);

        List<GameHistoryEntry> gameHistoryEntries = gameHistoryRepository.findByPlayerId(playerId);
        assertThat(gameHistoryEntries).hasSize(0);
        verify(roulette, never()).spin();
    }

    @Test
    void shouldPlaySlotGameAndUpdatePlayerBalanceAndSaveHistoryWhenWin() {
        //given
        SlotMachine slotMachine = new SlotMachine();
        SymbolGenerator stubSymbolGenerator = new StubSymbolGenerator();
        SlotMachineService slotMachineService = new SlotMachineService(slotMachine, stubSymbolGenerator);
        SlotMachineApplicationService slotMachineApplicationService =
                new SlotMachineApplicationService(slotMachineService);
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        PlayerId playerId = new PlayerId("1");
        playerRepository.save(new Player(playerId, new Balance(100)));
        GameHistoryRepository gameHistoryRepository = new InMemoryGameHistoryRepository();
        PlayerService playerService = new PlayerService(playerRepository);
        GameApplicationService gameApplicationService = new GameApplicationService(playerService, gameHistoryRepository);

        PreparedGame preparedGame = slotMachineApplicationService.prepare(new Amount(20), Bet.FIVE);

        //when
        GameOutcome result = gameApplicationService.playGame(playerId, preparedGame);

        //then
        Player updated = playerRepository.find(playerId);

        assertThat(updated.getBalance().getAmount()).isEqualTo(500);
        assertThat(result.payout()).isEqualTo(new Amount(500));

        List<GameHistoryEntry> gameHistoryEntries = gameHistoryRepository.findByPlayerId(playerId);
        assertThat(gameHistoryEntries).hasSize(1);

        GameHistoryEntry gameHistoryEntry = gameHistoryEntries.getFirst();
        assertThat(gameHistoryEntry.getPlayerId()).isEqualTo(new PlayerId("1"));
        assertThat(gameHistoryEntry.getOccurredAt()).isEqualTo(result.occurredAt());
        assertThat(gameHistoryEntry.getGameType()).isEqualTo(GameType.SLOT);
        assertThat(gameHistoryEntry.getDetails()).isEqualTo(result.details());
        assertThat(gameHistoryEntry.getCost()).isEqualTo(new Amount(100));
        assertThat(gameHistoryEntry.getPayout()).isEqualTo(new Amount(500));
        assertThat(gameHistoryEntry.getBalanceBefore()).isEqualTo(new Amount(100));
        assertThat(gameHistoryEntry.getBalanceAfter()).isEqualTo(new Amount(500));
    }
}