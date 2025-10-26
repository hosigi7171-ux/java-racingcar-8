package racingcar.config;

import racingcar.io.ConsoleInputReader;
import racingcar.io.InputReader;
import racingcar.io.ResultWriter;
import racingcar.service.RaceRoundManager;
import racingcar.service.RaceRoundManagerImpl;
import racingcar.validator.InputValidatorImpl;
import racingcar.domain.RacingGame;
import racingcar.io.ConsoleResultWriter;
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

    public RaceRoundManager raceRoundManager() {
        return new RaceRoundManagerImpl(new RandomNumberGenerator());
    }

    public RacingGame racingGame() {
        return new RacingGame(inputReader(), inputValidator(), resultWriter(), raceRoundManager());
    }
}
