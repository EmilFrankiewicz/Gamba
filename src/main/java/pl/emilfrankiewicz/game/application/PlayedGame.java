package pl.emilfrankiewicz.game.application;

import pl.emilfrankiewicz.game.domain.GameOutcome;
import pl.emilfrankiewicz.game.domain.Amount;

public record PlayedGame(Amount cost, GameOutcome gameOutcome) {
}
