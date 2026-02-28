package pl.emilfrankiewicz.game.domain;

public interface GameCostPolicy {
    int calculateCost(Bet bet);
}