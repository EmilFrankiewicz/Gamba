package pl.emilfrankiewicz.player.infrastructure;

import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;

public interface PlayerRepository {

    Player save(Player player);

    Player find(PlayerId id);
}
