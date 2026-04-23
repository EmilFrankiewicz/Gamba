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
    void shouldFindExistingPlayer() {
        PlayerRepository repo = new InMemoryPlayerRepository();
        PlayerService service = new PlayerService(repo);

        PlayerId id = new PlayerId("1");
        Player player = new Player(id, new Balance(10));
        repo.save(player);

        Player found = service.find(id);

        assertThat(found).isEqualTo(player);
    }

    @Test
    void shouldThrowWhenPlayerNotFound() {
        PlayerRepository repo = new InMemoryPlayerRepository();
        PlayerService service = new PlayerService(repo);

        PlayerId id = new PlayerId("1");

        Throwable thrown = catchThrowable(() -> service.find(id));

        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");
    }

    @Test
    void shouldSavePlayer() {
        PlayerRepository repo = new InMemoryPlayerRepository();
        PlayerService service = new PlayerService(repo);

        Player player = new Player(new PlayerId("1"), new Balance(10));

        Player saved = service.save(player);
        Player fromRepo = repo.find(player.getId());

        assertThat(saved).isEqualTo(player);
        assertThat(fromRepo).isEqualTo(player);
    }
}