package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.games.slotmachine.domain.*;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;

import java.time.Instant;
import java.util.List;

public class SlotMachineService {

    private final SlotMachine slotMachine;
    private final SymbolGenerator symbolGenerator;

    public SlotMachineService(SlotMachine slotMachine, SymbolGenerator symbolGenerator) {
        this.slotMachine = slotMachine;
        this.symbolGenerator = symbolGenerator;
    }

    public SpinOutcome spin() {
        List<Symbol> symbols = symbolGenerator.generateSymbols();
        EvaluationResult evaluation = slotMachine.evaluate(symbols);
        return new SpinOutcome(symbols, evaluation);
    }

    public GameResult gameResult() {
        SpinOutcome outcome = spin();
        Instant occurredAt = Instant.now();
        boolean win = outcome.evaluation().isWin();
        return new GameResult(win, outcome.symbols(), outcome.evaluation(), occurredAt);
    }

}
