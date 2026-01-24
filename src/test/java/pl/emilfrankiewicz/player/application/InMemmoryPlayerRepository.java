package pl.emilfrankiewicz.player.application;

import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemmoryPlayerRepository implements PlayerRepository {

    private final Map<PlayerId, Player> storage = new HashMap<>();

    @Override
    public Player save(Player player) {
        storage.put(player.getId(), player);
        return player;
    }

    @Override
    public Player find(PlayerId id) {
        return storage.get(id);
    }
}
