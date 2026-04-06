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

    public PlayerId getId() {
        return id;
    }

    public Player payForGame(int amount) {
        return decrease(amount);
    }

    public Player win(int amount) {
        return increase(amount);
    }

    private Player increase(int amount) {
        return new Player(this.id, balance.increase(amount));
    }

    private Player decrease(int amount) {
        return new Player(this.id, balance.decrease(amount));
    }


}
