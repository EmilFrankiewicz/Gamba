package pl.emilfrankiewicz.player.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.player.domain.Balance;
import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PlayerServiceTest {


    @Test
    void shouldIncreasePlayerBalance() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(0)));
        Player playerBeforeUpdate = mockDB.find(id);

        // when
        Player playerReturnedByService = playerService.win(id, 10);
        Player playerAfterUpdate = mockDB.find(id);

        // then
        assertThat(playerBeforeUpdate.getBalance()).isEqualTo(new Balance(0));
        assertThat(playerReturnedByService.getBalance()).isEqualTo(new Balance(10));
        assertThat(playerAfterUpdate.getBalance()).isEqualTo(new Balance(10));
        assertThat(playerBeforeUpdate).isNotSameAs(playerReturnedByService);
    }

    @Test
    void shouldDecreasePlayerBalance() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(15)));
        Player playerBeforeUpdate = mockDB.find(id);

        // when
        Player playerReturnedByService = playerService.payForGame(id, 10);
        Player playerAfterUpdate = mockDB.find(id);

        // then
        assertThat(playerBeforeUpdate.getBalance()).isEqualTo(new Balance(15));
        assertThat(playerReturnedByService.getBalance()).isEqualTo(new Balance(5));
        assertThat(playerAfterUpdate.getBalance()).isEqualTo(new Balance(5));
        assertThat(playerBeforeUpdate).isNotSameAs(playerReturnedByService);
    }

    @Test
    void shouldNotAllowBalanceBelowZero() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);

        PlayerId id = new PlayerId("1");
        mockDB.save(new Player(id, new Balance(10)));
        Player playerBeforeUpdate = mockDB.find(id);

        // when
        Throwable thrown = catchThrowable(() -> playerService.payForGame(id, 15));

        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Amount cannot be less than 0");
        assertThat(playerBeforeUpdate.getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldNotAllowIncreaseWhenPlayerNotFound() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);

        PlayerId existingId = new PlayerId("1");
        mockDB.save(new Player(existingId, new Balance(10)));

        PlayerId missingId = new PlayerId("2");

        // when
        Throwable thrown = catchThrowable(() -> playerService.win(missingId, 5));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");

        assertThat(mockDB.find(missingId)).isNull();

        assertThat(mockDB.find(existingId).getBalance()).isEqualTo(new Balance(10));
    }

    @Test
    void shouldNotAllowDecreaseWhenPlayerNotFound() {
        // given
        PlayerRepository mockDB = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(mockDB);

        PlayerId existingId = new PlayerId("1");
        mockDB.save(new Player(existingId, new Balance(10)));

        PlayerId missingId = new PlayerId("3");

        // when
        Throwable thrown = catchThrowable(() -> playerService.payForGame(missingId, 5));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");

        assertThat(mockDB.find(missingId)).isNull();

        assertThat(mockDB.find(existingId).getBalance()).isEqualTo(new Balance(10));
    }
}