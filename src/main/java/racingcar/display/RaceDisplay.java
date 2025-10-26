package racingcar.display;

import java.util.List;
import racingcar.domain.RacingCar;

public interface RaceDisplay {
    void showRaceStatus(List<RacingCar> carList);
}
