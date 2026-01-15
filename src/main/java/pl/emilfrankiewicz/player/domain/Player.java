package pl.emilfrankiewicz.player.domain;

public class Player {

    private final PlayerId id;
    private final Balance balance;

    public Player(PlayerId id, Balance balance) {
        this.id = id;
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }
}
