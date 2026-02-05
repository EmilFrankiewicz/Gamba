package pl.emilfrankiewicz.game.application;

import pl.emilfrankiewicz.game.domain.GamePayout;

public class FakePayout implements GamePayout {

    private final int amount;

    public FakePayout(int amount) {
        this.amount = amount;
    }

    @Override
    public int amount() {
        return amount;
    }
}
