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
import pl.emilfrankiewicz.games.slotmachine.domain.SlotMachine;
import pl.emilfrankiewicz.games.slotmachine.domain.SpinOutcome;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;
import pl.emilfrankiewicz.player.application.InMemoryPlayerRepository;
import pl.emilfrankiewicz.player.application.PlayerService;
import pl.emilfrankiewicz.player.domain.Balance;
import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;
import pl.emilfrankiewicz.games.slotmachine.domain.Bet;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class GameApplicationServiceTest {


    /*
    @Test
    void czyDzialaMojPomysl() {
        // given
        PlayerRepository playerRepo = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(playerRepo);
        PlayerId playerId = new PlayerId("1");
        playerRepo.save(new Player(playerId, new Balance(100)));

        PlacedBet placedBet = new PlacedBet(new Amount(10), new BetOnNumber(1));
        Roulette roulette = new FakeRoulette(1);
        PayoutCalculator payoutCalculator = new PayoutCalculator();
        BetEvaluator betEvaluator = new BetEvaluator();
        RouletteApplicationService rouletteApplicationService = new RouletteApplicationService(roulette, payoutCalculator, betEvaluator);

        PlayedGame playedGame = rouletteApplicationService.play(placedBet);

        GameHistoryRepository historyRepo = new InMemoryGameHistoryRepository();

        GameApplicationService gameApplicationService = new GameApplicationService(playerService, historyRepo);


        GameOutcome gameOutcome = gameApplicationService.playGame(playerId, playedGame);

        System.out.println(gameOutcome.details());
    }

    @Test
    void czyDzialaMojPomyslSlotMachineApplicationService() {
        // given

        PlayerRepository playerRepo = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(playerRepo);
        PlayerId playerId = new PlayerId("1");
        playerRepo.save(new Player(playerId, new Balance(100)));
        Player player = playerService.find(playerId);
        System.out.println(player.getBalance().getAmount());
        SlotMachine slotMachine = new SlotMachine();
        SymbolGenerator stubSymbolGenerator = new StubSymbolGenerator();
        SlotMachineService slotMachineService = new SlotMachineService(slotMachine, stubSymbolGenerator);


        SlotMachineApplicationService slotMachineApplicationService = new SlotMachineApplicationService(slotMachineService);

        Bet bet = Bet.FIVE;
        PlayedGame playedGame = slotMachineApplicationService.play(new Amount(20), bet);
        Player player3 = playerService.find(playerId);
        System.out.println(player3.getBalance().getAmount());
        GameHistoryRepository historyRepo = new InMemoryGameHistoryRepository();

        Amount sprawdzCzySianoMoze = slotMachineApplicationService.calculateCost(new Amount(20), bet);
        GameApplicationService gameApplicationService = new GameApplicationService(playerService, historyRepo);

        System.out.println(sprawdzCzySianoMoze + "moze?" + player3.getBalance().getAmount());

        GameOutcome gameOutcome = gameApplicationService.playGame(playerId, playedGame);


        Player player2 = playerService.find(playerId);
        System.out.println(player2.getBalance().getAmount());
        System.out.println(gameOutcome.payout());
    }















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
    } */


}