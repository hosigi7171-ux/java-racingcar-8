package racingcar;

import java.util.List;

public class RaceDisplay {

    /**
     * 한 차수에 해당하는 자동차 경주 현황을 보여준다
     * @param carList 현황을 볼 자동차 리스트
     */
    public void showRaceStatus(List<RacingCar> carList) {
        StringBuilder carStatusBuilder = new StringBuilder();

        String NEW_LINE = System.lineSeparator();

        for(RacingCar car : carList){
            carStatusBuilder.append(car.getName()).append(" : ");

            carStatusBuilder.append("-".repeat(Math.max(0, car.getForwardDistance())));
            carStatusBuilder.append("\n");
        }
        carStatusBuilder.append("\n");

        System.out.print(carStatusBuilder);
    }
}
