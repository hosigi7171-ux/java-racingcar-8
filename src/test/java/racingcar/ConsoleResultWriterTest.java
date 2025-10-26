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
import racingcar.domain.RacingCar;
import racingcar.io.ConsoleResultWriter;

public class ConsoleResultWriterTest {
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
    void 단일_우승자_출력기능_검증() {
        List<RacingCar> winnerList = new ArrayList<>();
        winnerList.add(new RacingCar("test2", 5));
        ConsoleResultWriter consoleResultWriter = new ConsoleResultWriter();

        consoleResultWriter.printWinner(winnerList);

        String expectedResult = "최종 우승자 : test2";
        assertThat(output()).isEqualTo(expectedResult);
    }

    @Test
    void 복수_우승자_출력기능_검증() {
        List<RacingCar> winnerList = new ArrayList<>();
        winnerList.add(new RacingCar("test2", 5));
        winnerList.add(new RacingCar("test3", 5));
        winnerList.add(new RacingCar("test4", 5));
        ConsoleResultWriter consoleResultWriter = new ConsoleResultWriter();

        consoleResultWriter.printWinner(winnerList);

        String expectedResult = "최종 우승자 : test2, test3, test4";
        assertThat(output()).isEqualTo(expectedResult);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
