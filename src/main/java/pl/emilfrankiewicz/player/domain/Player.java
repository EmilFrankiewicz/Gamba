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

    public Player increase(int amount) {
        return new Player(this.id, balance.increase(amount));
    }

    public Player decrease(int amount) {
        return new Player(this.id, balance.decrease(amount));
    }
}
