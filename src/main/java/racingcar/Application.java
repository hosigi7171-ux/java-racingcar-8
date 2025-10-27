package racingcar;

import racingcar.config.AppConfig;
import racingcar.domain.RacingGame;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        RacingGame game = config.racingGame();

        game.run();
    }
}
