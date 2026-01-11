package pl.emilfrankiewicz.games.slotmachine.application;

import pl.emilfrankiewicz.games.slotmachine.domain.EvaluationResult;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotMachine;
import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;

import java.util.List;

public class SlotMachineService {

    private final SlotMachine slotMachine;
    private final SymbolGenerator symbolGenerator;

    public SlotMachineService(SlotMachine slotMachine, SymbolGenerator symbolGenerator) {
        this.slotMachine = slotMachine;
        this.symbolGenerator = symbolGenerator;
    }

    public EvaluationResult spin() {
        List<Symbol> symbols = symbolGenerator.generateSymbols();
        return slotMachine.evaluate(symbols);
    }
}
