package pl.emilfrankiewicz.player.domain;

import pl.emilfrankiewicz.game.domain.Amount;

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

    public Player payForGame(Amount amount) {
        return decrease(amount.getAmount());
    }

    public Player win(Amount amount) {
        return increase(amount.getAmount());
    }

    public Player applyPayout(Amount finalPayout) {
        return finalPayout.getAmount() > 0
                ? win(finalPayout)
                : this;
    }

    private Player increase(int amount) {
        return new Player(this.id, balance.increase(amount));
    }

    private Player decrease(int amount) {
        return new Player(this.id, balance.decrease(amount));
    }

}
