package pl.emilfrankiewicz.player.application;

import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player increaseBalance(PlayerId id, int balance) {
        Player player = playerRepository.find(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        Player updated = player.increase(balance);
        return playerRepository.save(updated);
    }

    public Player decreaseBalance(PlayerId id, int balance) {
        Player player = playerRepository.find(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        Player updated = player.decrease(balance);
        return playerRepository.save(updated);
    }
}