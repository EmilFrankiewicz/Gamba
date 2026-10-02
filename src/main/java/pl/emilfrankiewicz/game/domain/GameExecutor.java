package pl.emilfrankiewicz.game.domain;

@FunctionalInterface
public interface GameExecutor {
    GameOutcome play();
}