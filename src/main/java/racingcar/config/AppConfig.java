package racingcar.config;

import racingcar.io.ConsoleInputReader;
import racingcar.io.InputReader;
import racingcar.io.ResultWriter;
import racingcar.validator.InputValidatorImpl;
import racingcar.domain.RacingGame;
import racingcar.io.ConsoleResultWriter;
import racingcar.generator.NumberGenerator;
import racingcar.generator.RandomNumberGenerator;

public class AppConfig {
    public InputReader inputReader() {
        return new ConsoleInputReader();
    }

    public InputValidatorImpl inputValidator() {
        return new InputValidatorImpl();
    }

    public ResultWriter resultWriter() {
        return new ConsoleResultWriter();
    }

    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    public RacingGame racingGame() {
        return new RacingGame(inputReader(), inputValidator(), resultWriter(), numberGenerator());
    }
}
