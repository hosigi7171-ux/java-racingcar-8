package racingcar.display;

import java.util.List;
import racingcar.domain.RacingCar;

/**
 * 한 라운드의 경기 현황을 보여주는 클래스
 * 얘)
 * car1 : --
 * car2 : ----
 * car3 : ---
 */
public class ConsoleRaceDisplay implements RaceDisplay{

    @Override
    public void showRaceStatus(List<RacingCar> carList) {
        StringBuilder carStatusBuilder = new StringBuilder();

        for(RacingCar car : carList){
            carStatusBuilder.append(car.getName()).append(" : ");

            carStatusBuilder.append("-".repeat(Math.max(0, car.getForwardDistance())));
            carStatusBuilder.append("\n");
        }
        carStatusBuilder.append("\n");

        System.out.print(carStatusBuilder);
    }
}
