package pl.emilfrankiewicz.player.application;

import pl.emilfrankiewicz.player.domain.Player;
import pl.emilfrankiewicz.player.domain.PlayerId;
import pl.emilfrankiewicz.player.infrastructure.PlayerRepository;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player win(PlayerId id, int balance) {
        Player player = playerRepository.find(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        Player updated = player.win(balance);
        return playerRepository.save(updated);
    }

    public Player payForGame(PlayerId id, int balance) {
        Player player = playerRepository.find(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        Player updated = player.payForGame(balance);
        return playerRepository.save(updated);
    }

    public Player find(PlayerId id) {
        Player player = playerRepository.find(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found");
        }
        return player;
    }
}