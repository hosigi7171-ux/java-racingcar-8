package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputReaderTest {
    private InputReader inputReader;

    @BeforeEach
    void setup() {
        inputReader = new InputReader();
    }

    @Test
    void 자동차_이름들을_올바르게_입력받는지_검증() {
        String input = "car1,car2,car3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String result = inputReader.readCarNames();

        assertThat(result).isEqualTo(input);
    }

    @Test
    void 시도횟수를_올바르게_입력받는지_검증() {
        String input = "5";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);

        String result = inputReader.readTryCount();

        assertThat(result).isEqualTo(input);
    }

    @AfterEach
    void tearDown(){
        System.setIn(System.in);
    }
}