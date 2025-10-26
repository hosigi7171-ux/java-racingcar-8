package racingcar.stub;

import java.util.List;
import racingcar.display.RaceDisplay;
import racingcar.domain.RacingCar;

public class StubRaceDisplay implements RaceDisplay {

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
