package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.display.ConsoleRaceDisplay;
import racingcar.display.RaceDisplay;
import racingcar.domain.RacingCar;

public class RaceDisplayTest {
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
    void 경주_현황을_검증() {
        List<RacingCar> carList = new ArrayList<>();
        carList.add(new RacingCar("test1", 3));
        carList.add(new RacingCar("test2", 5));
        carList.add(new RacingCar("test3", 2));

        RaceDisplay raceDisplay = new ConsoleRaceDisplay();
        raceDisplay.showRaceStatus(carList);

        String expectedResult =
            "test1 : ---\n" +
            "test2 : -----\n" +
            "test3 : --\n" +
            "\n";

        assertThat(output()).isEqualTo(expectedResult);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
