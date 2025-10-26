package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.RacingCar;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RacingCarTest {
    private RacingCar car;

    @BeforeEach
    void 자동차_객체_세팅() {
        car = new RacingCar("testDriver");
    }

    @Test
    void 자동차가_전진시_전진거리_증가한다() {
        car.moveIfAtLeastFour(4);

        assertThat(car.getForwardDistance()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void 자동차가_4이상의_숫자를_받으면_전진한다(int forwardNumber) {
        car.moveIfAtLeastFour(forwardNumber);

        assertThat(car.getForwardDistance()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void 자동차가_4미만의_숫자를_받으면_전진하지_않는다(int stopNumber) {
        car.moveIfAtLeastFour(stopNumber);

        assertThat(car.getForwardDistance()).isEqualTo(0);
    }
}
