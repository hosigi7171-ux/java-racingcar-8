package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingGame;
import racingcar.generator.NumberGenerator;
import racingcar.io.InputReader;
import racingcar.io.ResultWriter;
import racingcar.stub.StubConsoleInputReader;
import racingcar.stub.StubConsoleResultWriter;
import racingcar.stub.AlwaysGoGenerator;
import racingcar.stub.StubInputValidator;
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
        NumberGenerator numberGenerator = new AlwaysGoGenerator();
        RacingGame game = new RacingGame(inputReader, inputValidator, resultWriter, numberGenerator);

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
