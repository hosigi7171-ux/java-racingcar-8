package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static racingcar.constants.RaceTestConstants.STOP;
import static racingcar.constants.RaceTestConstants.MOVING_FORWARD;
import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD.getValue(), STOP.getValue()
        );
    }

    @Test
    void 기능_테스트_복수의_시도횟수() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("test1,test2,test3", "3");
                    assertThat(output()).contains(
                            "test1 : -", "test2 : -", "test3 : -",
                            "test1 : --", "test2 : --", "test3 : -",
                            "test1 : --", "test2 : ---", "test3 : -",
                            "최종 우승자 : test2"
                    );
                },
                MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue(),
                MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue(), STOP.getValue(),
                STOP.getValue(), MOVING_FORWARD.getValue(), STOP.getValue()
        );
    }

    @Test
    void 우승자_복수일떄_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("test1,test2,test3", "1");
                    assertThat(output()).contains("test1 : -", "test2 : -", "test3 : -",
                            "최종 우승자 : test1, test2, test3");
                },
                MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue()
        );
    }

    @Test
    void 예외_테스트_5글자_이상() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 이름이_빈문자열() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",test2", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 이름이_공백() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("  ,test2", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_음수_시도횟수() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("test1,test2", "-2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_문자_시도횟수() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("test1,test2", "AAA"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
