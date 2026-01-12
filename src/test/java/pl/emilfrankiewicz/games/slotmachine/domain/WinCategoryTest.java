package pl.emilfrankiewicz.games.slotmachine.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class WinCategoryTest {

    @ParameterizedTest
    @CsvSource({"100, VERY_HIGH", "50, HIGH", "20, MEDIUM", "10, LOW", "5, VERY_LOW", "0, LOSS"})
    void shouldMapPayoutToCorrectCategory(int payout, WinCategory expected) {
        WinCategory result = WinCategory.fromPayout(payout);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenPayoutIsNotSupported() {
        // given & when
        Throwable thrown = catchThrowable(() -> WinCategory.fromPayout(99));

        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class).hasMessage("Unexpected value: 99");
    }
}