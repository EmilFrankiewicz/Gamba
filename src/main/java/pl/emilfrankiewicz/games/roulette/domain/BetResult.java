package pl.emilfrankiewicz.games.roulette.domain;

import java.time.Instant;

public record BetResult(
        boolean isWin,
        Amount winAmount,
        PlacedBet placedBet,
        RouletteResult rouletteResult,
        Instant occurredAt
) {

    public BetResult(
            boolean isWin,
            Amount winAmount,
            PlacedBet placedBet,
            RouletteResult rouletteResult
    ) {
        this(
                isWin,
                winAmount,
                placedBet,
                rouletteResult,
                Instant.now()
        );
    }

    public Amount payout() {
        if (!isWin) {
            return new Amount(0);
        }
        return new Amount(winAmount.getAmount() + placedBet.amount().getAmount());
    }
}