package pl.emilfrankiewicz.game.domain;

public class StandardGameCostPolicy implements GameCostPolicy {

    private final int baseCost;

    public StandardGameCostPolicy(int baseCost) {
        this.baseCost = baseCost;
    }

    @Override
    public int calculateCost(Bet bet) {
        return bet.multiplier() * baseCost;
    }
}