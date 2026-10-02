package pl.emilfrankiewicz.game.domain;

public record PreparedGame(
        Amount cost,
        GameExecutor executor
) {
    public GameOutcome play() {
        return executor.play();
    }
}