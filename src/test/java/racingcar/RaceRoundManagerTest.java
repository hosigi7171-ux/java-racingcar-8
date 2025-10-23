package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.generator.NumberGenerator;

public class RaceRoundManagerTest {
    private PrintStream standardOut;
    private OutputStream captor;

    private static final int MOVING_FORWARD = 4; // 숫자 생성기용 전진 숫자
    private static final int STOP = 3; // 숫자 생성기용 정지 숫자
    private static final int STOP_POSITION = 0; // 더미 데이터용 정지일때의 위치
    private static final int MOVE_POSITION = 1; // 더미 데이터용 전진시 위치

    /**
     * 테스트용 전진하는 숫자 생성기
     */
    private static class GoNumberGenerator implements NumberGenerator {
        @Override
        public int generate() {
            return MOVING_FORWARD;
        }
    }

    /**
     * 테스트용 스톱하는 숫자 생성기
     */
    private static class StopNumberGenerator implements NumberGenerator {
        @Override
        public int generate() {
            return STOP;
        }
    }

    /**
     * 테스트용 연속된 숫자 생성기
     */
    private static class SequentialNumberGenerator implements NumberGenerator {
        private final Iterator<Integer> numbers;

        SequentialNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers.iterator();
        }

        @Override
        public int generate() {
            if (numbers.hasNext()) {
                return numbers.next();
            }

            return 0;
        }
    }

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
        RacingCar car = new RacingCar("test", STOP_POSITION);
        NumberGenerator goNumberGenerator = new GoNumberGenerator();
        RaceRoundManager manager = new RaceRoundManager(goNumberGenerator);

        boolean result = manager.goOrStop(car);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 자동차_정지를_결정하는_로직_검증() {
        RacingCar car = new RacingCar("test", STOP_POSITION);
        NumberGenerator stopNumberGenerator = new StopNumberGenerator();
        RaceRoundManager manager = new RaceRoundManager(stopNumberGenerator);

        boolean result = manager.goOrStop(car);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 한_라운드의_실행이_정상_작동하는지_검증() {
        /* Given */
        RacingCar car1 = new RacingCar("test1", STOP_POSITION);
        RacingCar car2 = new RacingCar("test2", STOP_POSITION);
        RacingCar car3 = new RacingCar("test3", STOP_POSITION);
        List<RacingCar> carList = new ArrayList<>(Arrays.asList(car1, car2, car3));

        List<Integer> numbers = Arrays.asList(STOP, STOP, MOVING_FORWARD);
        NumberGenerator generator = new SequentialNumberGenerator(numbers);
        RaceRoundManager manager = new RaceRoundManager(carList, generator);

        /* When */
        manager.progressRound();

        /* Then */
        String consoleExpectedResult = """
                test1 : 
                test2 : 
                test3 : -
                                
                """;
        // 자동차 객체 업데이트 검증
        assertThat(car1.getForwardDistance()).isEqualTo(STOP_POSITION);
        assertThat(car2.getForwardDistance()).isEqualTo(STOP_POSITION);
        assertThat(car3.getForwardDistance()).isEqualTo(MOVE_POSITION);

        // 콘솔 출력 검증
        assertThat(output()).isEqualTo(consoleExpectedResult);
    }

    @Test
    void 레이스를_정상적으로_수행하는지_검증() {
        /* Given */
        int turnCount = 3;
        RacingCar car1 = new RacingCar("test1", STOP_POSITION);
        RacingCar car2 = new RacingCar("test2", STOP_POSITION);
        RacingCar car3 = new RacingCar("test3", STOP_POSITION);
        List<RacingCar> carList = new ArrayList<>(Arrays.asList(car1, car2, car3));

        List<Integer> numbers = Arrays.asList(
                STOP, STOP, MOVING_FORWARD,
                MOVING_FORWARD, STOP, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );

        NumberGenerator generator = new SequentialNumberGenerator(numbers);
        RaceRoundManager manager = new RaceRoundManager(carList, generator);

        /* When */
        manager.runRace(turnCount);
        List<RacingCar> winnerList = manager.getWinnerList();

        /* Then */
        String consoleExpectedResult = """
                test1 : 
                test2 : 
                test3 : -
                    
                test1 : -
                test2 : 
                test3 : --
                             
                test1 : --
                test2 : -
                test3 : --
                
                """;
        // 자동차 객체 업데이트 검증
        assertThat(car1.getForwardDistance()).isEqualTo(STOP_POSITION + 2);
        assertThat(car2.getForwardDistance()).isEqualTo(STOP_POSITION);
        assertThat(car3.getForwardDistance()).isEqualTo(STOP_POSITION + 2);
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
