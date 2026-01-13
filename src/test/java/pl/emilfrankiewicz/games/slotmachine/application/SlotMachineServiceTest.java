package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotMachine;
import pl.emilfrankiewicz.games.slotmachine.domain.SpinOutcome;
import pl.emilfrankiewicz.games.slotmachine.domain.Symbol;
import pl.emilfrankiewicz.games.slotmachine.domain.WinCategory;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SlotMachineServiceTest {

    private final SlotMachine slotMachine = new SlotMachine();
    private final SymbolGenerator stubSymbolGenerator = new StubSymbolGenerator();
    private final SlotMachineService slotMachineService = new SlotMachineService(slotMachine, stubSymbolGenerator);

    @Test
    void shouldReturnResultFromSlotMachine() {
        // when
        SpinOutcome outcome = slotMachineService.spin();

        // then
        assertThat(outcome.symbols()).isEqualTo(List.of(Symbol.SEVEN, Symbol.SEVEN, Symbol.SEVEN));
        assertThat(outcome.evaluation().payout()).isEqualTo(100);
        assertThat(outcome.evaluation().winCategory()).isEqualTo(WinCategory.VERY_HIGH);
    }
}