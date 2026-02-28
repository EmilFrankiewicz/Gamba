package pl.emilfrankiewicz.game.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StandardGameCostPolicyTest {

    private final GameCostPolicy gameCostPolicy = new StandardGameCostPolicy(25);

    @ParameterizedTest
    @CsvSource({
            "ONE, 25",
            "TWO, 50",
            "FIVE, 125",
            "TEN, 250"
    })
    void shouldCalculateCost(Bet bet, int expectedCost) {
        assertThat(gameCostPolicy.calculateCost(bet)).isEqualTo(expectedCost);
    }
}