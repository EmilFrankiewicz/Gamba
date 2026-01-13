package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.games.slotmachine.domain.*;
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

    @Test
    void shouldCreateGameResultBasedOnSpinOutcome() {
        // when
        GameResult result = slotMachineService.gameResult();

        //then
        assertThat(result.win()).isTrue();
        assertThat(result.symbols()).isEqualTo(List.of(Symbol.SEVEN, Symbol.SEVEN, Symbol.SEVEN));
        assertThat(result.evaluation().payout()).isEqualTo(100);
        assertThat(result.evaluation().winCategory()).isEqualTo(WinCategory.VERY_HIGH);
        assertThat(result.occurredAt()).isNotNull();
    }
}