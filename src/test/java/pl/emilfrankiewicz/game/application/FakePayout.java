package pl.emilfrankiewicz.game.application;

import pl.emilfrankiewicz.game.domain.BasePayout;

public class FakePayout implements BasePayout {

    private final int amount;

    public FakePayout(int amount) {
        this.amount = amount;
    }

    @Override
    public int amount() {
        return amount;
    }
}
