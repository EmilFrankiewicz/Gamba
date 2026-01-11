package pl.emilfrankiewicz.games.slotmachine.application;

import org.junit.jupiter.api.Test;
import pl.emilfrankiewicz.games.slotmachine.domain.EvaluationResult;
import pl.emilfrankiewicz.games.slotmachine.domain.SlotMachine;
import pl.emilfrankiewicz.games.slotmachine.domain.WinCategory;
import pl.emilfrankiewicz.games.slotmachine.infrastructure.SymbolGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class SlotMachineServiceTest {

    private final SlotMachine slotMachine = new SlotMachine();
    private final SymbolGenerator stubSymbolGenerator = new StubSymbolGenerator();
    private final SlotMachineService slotMachineService = new SlotMachineService(slotMachine, stubSymbolGenerator);

    @Test
    public void shouldReturnResultFromSlotMachine() {
        // when
        EvaluationResult evaluationResult = slotMachineService.spin();

        // then
        assertThat(evaluationResult.payout()).isEqualTo(100);
        assertThat(evaluationResult.winCategory()).isEqualTo(WinCategory.VERY_HIGH);
    }
}