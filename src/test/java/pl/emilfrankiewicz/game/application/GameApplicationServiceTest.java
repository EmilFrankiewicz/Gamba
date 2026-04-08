package pl.emilfrankiewicz.game.application;

import static org.assertj.core.api.Assertions.assertThat;

class GameApplicationServiceTest {

    /*
    @Test
    void shouldIncreasePlayerBalanceWhenPayoutIsPositive() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);
        GameApplicationService gameService = new GameApplicationService(playerService);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(0)));
        GamePayout payout = new FakePayout(10);

        // when
        gameService.applyResult(id, payout);

        // then
        assertThat(mockDB.find(id).getBalance().getAmount()).isEqualTo(10);
    }

    @Test
    void shouldDecreasePlayerBalanceWhenPayoutIsNegative() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);
        GameApplicationService gameService = new GameApplicationService(playerService);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(15)));
        GamePayout payout = new FakePayout(-10);

        // when
        gameService.applyResult(id, payout);

        // then
        assertThat(mockDB.find(id).getBalance().getAmount()).isEqualTo(5);
    }

    @Test
    void shouldNotChangeBalanceWhenPayoutIsZero() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);
        GameApplicationService gameService = new GameApplicationService(playerService);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(5)));
        GamePayout payout = new FakePayout(0);

        // when
        gameService.applyResult(id, payout);

        // then
        assertThat(mockDB.find(id).getBalance().getAmount()).isEqualTo(5);
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotFound() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);
        GameApplicationService gameService = new GameApplicationService(playerService);

        PlayerId id = new PlayerId("100");
        GamePayout payout = new FakePayout(10);

        // when
        Throwable thrown = catchThrowable(() -> gameService.applyResult(id, payout));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");
    }

     */
}