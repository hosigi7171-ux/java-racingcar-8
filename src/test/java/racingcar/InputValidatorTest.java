package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    private InputValidator validator;

    @BeforeEach
    void 검증기_생성() {
        validator = new InputValidator();
    }

    @Test
    void 자동차_이름이_5자_이하일때_검증() {
        String validNames = "test1, test2";

        assertThatCode(() -> {
            validator.validateCarNames(validNames);
        }).doesNotThrowAnyException();
    }

    @Test
    void 자동차_이름이_5자_초과일때_예외_발생() {
        String invalidNames = "test1, invalid2";

        assertThatThrownBy(() -> {
            validator.validateCarNames(invalidNames);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("자동차 이름은 5자 이하여야 합니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1000"})
    void 시도횟수가_0이상일때_검증(String tryCount) {
        assertThatCode(() -> {
            validator.validateTryCount(tryCount);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-1000"})
    void 시도횟수가_음수일때_예외_발생(String tryCount) {
        assertThatThrownBy(() -> {
            validator.validateTryCount(tryCount);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("시도횟수는 음수가 될 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"+5000", "3이아님"})
    void 시도횟수가_숫자가_아닐때_예외_발생(String tryCount) {
        assertThatThrownBy(() -> {
            validator.validateTryCount(tryCount);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("시도횟수는 숫자여야 합니다");
    }
}
