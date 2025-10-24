package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.generator.NumberGenerator;

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

    private class StubInputReader extends InputReader{
        @Override
        public String readCarNames(){
            return "test1,test2";
        }

        @Override
        public String readTryCount(){
            return "3";
        }
    }

    private class StubInputValidator extends InputValidator{
        @Override
        public void validateCarNames(String names){}

        @Override
        public void validateTryCount(String tryCount){}
    }

    private class StubResultWriter extends ResultWriter{
        @Override
        public void printWinner(List<RacingCar> winnerList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("최종 우승자 : ");

            for (int index = 0; index < winnerList.size(); index++) {
                stringBuilder.append(winnerList.get(index).getName());

                if (index != winnerList.size() - 1) {
                    stringBuilder.append(", ");
                }
            }

            System.out.println(stringBuilder);
        }
    }

    private class AlwaysGoGenerator implements NumberGenerator{

        @Override
        public int generate() {
            return 5;
        }
    }

    @Test
    void run_메서드_기능_테스트(){
        // Given
        StubInputReader inputReader = new StubInputReader();
        StubInputValidator inputValidator = new StubInputValidator();
        StubResultWriter resultWriter = new StubResultWriter();
        AlwaysGoGenerator numberGenerator = new AlwaysGoGenerator();
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
