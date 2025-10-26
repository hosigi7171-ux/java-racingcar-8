package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.RacingCar;
import racingcar.display.ConsoleRaceDisplay;
import racingcar.display.RaceDisplay;
import racingcar.generator.NumberGenerator;

public class RaceRoundManagerImpl implements RaceRoundManager {
    private final NumberGenerator numberGenerator;
    private final RaceDisplay raceDisplay;
    private List<RacingCar> carList;

    public RaceRoundManagerImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        raceDisplay = new ConsoleRaceDisplay();
    }

    public RaceRoundManagerImpl(List<RacingCar> carList, NumberGenerator numberGenerator) {
        this.carList = carList;
        this.numberGenerator = numberGenerator;
        raceDisplay = new ConsoleRaceDisplay();
    }

    public boolean goOrStop(RacingCar car) {
        int generatedNumber = numberGenerator.generate();
        car.moveIfAtLeastFour(generatedNumber);
        return generatedNumber >= 4;
    }

    public List<RacingCar> findWinners() {
        List<RacingCar> winners = new ArrayList<>();
        int maxDistance = 0;

        for (RacingCar car : carList) {
            int distance = car.getForwardDistance();
            if (distance > maxDistance) {
                winners.clear();
                winners.add(car);
                maxDistance = distance;
            } else if (distance == maxDistance) {
                winners.add(car);
            }
        }

        return winners;
    }

    public void progressRound() {
        for (RacingCar car : carList) {
            goOrStop(car);
        }
        raceDisplay.showRaceStatus(carList);
    }

    public void runRace(int turnCount) {
        for (int turnIndex = 0; turnIndex < turnCount; turnIndex++) {
            progressRound();
        }
    }
}
