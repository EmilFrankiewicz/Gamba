package pl.emilfrankiewicz.games.slotmachine.domain;

public record EvaluationResult(int payout, WinCategory winCategory) {

    public boolean isWin() {
        return payout != 0;
    }
}


