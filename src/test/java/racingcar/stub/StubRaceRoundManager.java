package racingcar.stub;

import java.util.List;
import racingcar.constants.RaceTestConstants;
import racingcar.display.RaceDisplay;
import racingcar.domain.RacingCar;
import racingcar.service.RaceRoundManager;

public class StubRaceRoundManager implements RaceRoundManager {
    private final RaceDisplay raceDisplay;

    public StubRaceRoundManager(RaceDisplay raceDisplay) {
        this.raceDisplay = raceDisplay;
    }

    @Override
    public boolean goOrStop(RacingCar car) {
        car.moveIfAtLeastFour(RaceTestConstants.MOVING_FORWARD.getValue());
        return true;
    }

    @Override
    public List<RacingCar> findWinners(List<RacingCar> cars) {
        return cars; // 모두 우승시키기
    }

    @Override
    public void progressRound(List<RacingCar> cars) {
        for (RacingCar car : cars) {
            goOrStop(car);
        }
        raceDisplay.showRaceStatus(cars);
    }

    @Override
    public void runRace(int turnCount, List<RacingCar> cars) {
        for (int index = 0; index < turnCount; index++) {
            progressRound(cars);
        }
    }
}
