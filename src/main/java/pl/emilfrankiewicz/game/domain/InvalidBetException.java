package pl.emilfrankiewicz.game.domain;

public class InvalidBetException extends RuntimeException {
    public InvalidBetException(String message) {
        super(message);
    }
}
