package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static racingcar.constants.RaceTestConstants.STOP;
import static racingcar.constants.RaceTestConstants.MOVING_FORWARD;
import static racingcar.constants.RaceTestConstants.STOPPED_POSITION;
import static racingcar.constants.RaceTestConstants.MOVED_POSITION;

import racingcar.domain.RacingCar;
import racingcar.generator.NumberGenerator;
import racingcar.service.RaceRoundManager;
import racingcar.service.RaceRoundManagerImpl;
import racingcar.stub.AlwaysGoGenerator;
import racingcar.stub.SequentialNumberGenerator;
import racingcar.stub.StopNumberGenerator;

public class RaceRoundManagerTest {
    private PrintStream standardOut;
    private OutputStream captor;

    private String output() {
        return captor.toString();
    }

    @BeforeEach
    void setup() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @Test
    void 자동차_전진을_결정하는_로직_검증() {
        RacingCar car = new RacingCar("test", STOPPED_POSITION.getValue());
        NumberGenerator goNumberGenerator = new AlwaysGoGenerator();
        RaceRoundManager manager = new RaceRoundManagerImpl(goNumberGenerator);

        boolean result = manager.goOrStop(car);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 자동차_정지를_결정하는_로직_검증() {
        RacingCar car = new RacingCar("test", STOPPED_POSITION.getValue());
        NumberGenerator stopNumberGenerator = new StopNumberGenerator();
        RaceRoundManagerImpl manager = new RaceRoundManagerImpl(stopNumberGenerator);

        boolean result = manager.goOrStop(car);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 한_라운드의_실행이_정상_작동하는지_검증() {
        /* Given */
        RacingCar car1 = new RacingCar("test1", STOPPED_POSITION.getValue());
        RacingCar car2 = new RacingCar("test2", STOPPED_POSITION.getValue());
        RacingCar car3 = new RacingCar("test3", STOPPED_POSITION.getValue());
        List<RacingCar> carList = new ArrayList<>(Arrays.asList(car1, car2, car3));

        List<Integer> numbers = Arrays.asList(STOP.getValue(), STOP.getValue(),
                MOVING_FORWARD.getValue());
        NumberGenerator generator = new SequentialNumberGenerator(numbers);
        RaceRoundManagerImpl manager = new RaceRoundManagerImpl(generator);

        /* When */
        manager.progressRound(carList);

        /* Then */
        String consoleExpectedResult = """
               test1 :\s
               test2 :\s
               test3 : -
               
               """;
        // 자동차 객체 업데이트 검증
        assertThat(car1.getForwardDistance()).isEqualTo(STOPPED_POSITION.getValue());
        assertThat(car2.getForwardDistance()).isEqualTo(STOPPED_POSITION.getValue());
        assertThat(car3.getForwardDistance()).isEqualTo(MOVED_POSITION.getValue());

        // 콘솔 출력 검증
        assertThat(output()).isEqualTo(consoleExpectedResult);
    }

    @Test
    void 레이스를_정상적으로_수행하는지_검증() {
        /* Given */
        int turnCount = 3;
        RacingCar car1 = new RacingCar("test1", STOPPED_POSITION.getValue());
        RacingCar car2 = new RacingCar("test2", STOPPED_POSITION.getValue());
        RacingCar car3 = new RacingCar("test3", STOPPED_POSITION.getValue());
        List<RacingCar> carList = new ArrayList<>(Arrays.asList(car1, car2, car3));

        List<Integer> numbers = Arrays.asList(
                STOP.getValue(), STOP.getValue(), MOVING_FORWARD.getValue(),
                MOVING_FORWARD.getValue(), STOP.getValue(), MOVING_FORWARD.getValue(),
                MOVING_FORWARD.getValue(), MOVING_FORWARD.getValue(), STOP.getValue()
        );

        NumberGenerator generator = new SequentialNumberGenerator(numbers);
        RaceRoundManagerImpl manager = new RaceRoundManagerImpl(generator);

        /* When */
        manager.runRace(turnCount, carList);
        List<RacingCar> winnerList = manager.findWinners(carList);

        /* Then */
        String consoleExpectedResult = """
               test1 :\s
               test2 :\s
               test3 : -
               
               test1 : -
               test2 :\s
               test3 : --
               
               test1 : --
               test2 : -
               test3 : --
               
               """;
        // 자동차 객체 업데이트 검증
        assertThat(car1.getForwardDistance()).isEqualTo(STOPPED_POSITION.getValue() + 2);
        assertThat(car2.getForwardDistance()).isEqualTo(STOPPED_POSITION.getValue() + 1);
        assertThat(car3.getForwardDistance()).isEqualTo(STOPPED_POSITION.getValue() + 2);
        // 우승자 검증
        assertThat(winnerList.getFirst().getName()).isEqualTo("test1");
        assertThat(winnerList.get(1).getName()).isEqualTo("test3");
        // 콘솔 출력 검증
        assertThat(output()).isEqualTo(consoleExpectedResult);
    }


    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
