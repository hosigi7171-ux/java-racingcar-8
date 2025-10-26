package racingcar.display;

import java.util.List;
import racingcar.domain.RacingCar;

public interface RaceDisplay {

    /**
     * 한 차수에 해당하는 자동차 경주 현황을 보여준다
     * @param carList 현황을 볼 자동차 리스트
     */
    void showRaceStatus(List<RacingCar> carList);
}
