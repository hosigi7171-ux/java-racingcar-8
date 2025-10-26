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
import racingcar.constants.RaceTestConstants;
import racingcar.display.RaceDisplay;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.io.InputReader;
import racingcar.io.ResultWriter;
import racingcar.service.RaceRoundManager;
import racingcar.stub.StubConsoleInputReader;
import racingcar.stub.StubConsoleResultWriter;
import racingcar.stub.StubInputValidator;
import racingcar.stub.StubRaceDisplay;
import racingcar.stub.StubRaceRoundManager;
import racingcar.validator.InputValidator;

public class RacingGameTest {
    private PrintStream standardOut;
    private OutputStream captor;

    private String output() {
        return captor.toString().trim();
    }

    @BeforeEach
    void setup() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @Test
    void run_메서드_기능_테스트() {
        // Given
        InputReader inputReader = new StubConsoleInputReader();
        InputValidator inputValidator = new StubInputValidator();
        ResultWriter resultWriter = new StubConsoleResultWriter();

        List<RacingCar> cars = new ArrayList<>();
        cars.add(new RacingCar("test1", RaceTestConstants.STOPPED_POSITION.getValue()));
        cars.add(new RacingCar("test2", RaceTestConstants.STOPPED_POSITION.getValue()));

        RaceDisplay raceDisplay = new StubRaceDisplay();
        RaceRoundManager raceRoundManager = new StubRaceRoundManager(raceDisplay);
        RacingGame game = new RacingGame(inputReader, inputValidator, resultWriter, raceRoundManager);

        // When
        game.run();

        // Then
        String expectedResult = "test1 : -\n"
                + "test2 : -\n"
                + "\n"
                + "test1 : --\n"
                + "test2 : --\n"
                + "\n"
                + "test1 : ---\n"
                + "test2 : ---\n"
                + "\n"
                + "최종 우승자 : test1, test2";
        assertThat(output()).isEqualTo(expectedResult);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
