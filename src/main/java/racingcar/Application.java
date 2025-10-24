package racingcar;

import racingcar.generator.NumberGenerator;
import racingcar.generator.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        InputValidator validator = new InputValidator();
        ResultWriter writer = new ResultWriter();
        NumberGenerator generator = new RandomNumberGenerator();
        RacingGame game = new RacingGame(reader, validator, writer, generator);

        game.run();
    }
}
