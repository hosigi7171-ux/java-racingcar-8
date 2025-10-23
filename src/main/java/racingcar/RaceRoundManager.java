package racingcar;

import java.util.ArrayList;
import java.util.List;
import racingcar.display.ConsoleRaceDisplay;
import racingcar.display.RaceDisplay;
import racingcar.generator.NumberGenerator;

public class RaceRoundManager {
    private final NumberGenerator numberGenerator;
    private final RaceDisplay raceDisplay;
    private List<RacingCar> carList;

    public RaceRoundManager(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        raceDisplay = new ConsoleRaceDisplay();
    }

    public RaceRoundManager(List<RacingCar> carList, NumberGenerator numberGenerator) {
        this.carList = carList;
        this.numberGenerator = numberGenerator;
        raceDisplay = new ConsoleRaceDisplay();
    }

    /**
     * 자동차를 전진 또는 정지 시킨다
     * @param car 전진 또는 정지시킬 자동차
     * @return 전진시 true 리턴, 정지시 false 리턴
     */
    public boolean goOrStop(RacingCar car){
        int generatedNumber = numberGenerator.generate();
        car.moveIfAtLeastFour(generatedNumber);
        return generatedNumber >= 4;
    }


    /**
     * 우승자를 찾아주는 메서드
     * @return 우승자 리스트 반환 (동점자도 우승자로 간주)
     */
    public List<RacingCar> findWinners(){
        List<RacingCar> winners = new ArrayList<>();
        int maxDistance = 0;

        for(RacingCar car : carList){
            int distance = car.getForwardDistance();
            if(distance > maxDistance){
                winners.clear();
                winners.add(car);
                maxDistance = distance;
            }else if(distance == maxDistance){
                winners.add(car);
            }
        }

        return winners;
    }

    /**
     * 한 라운드의 경주를 진행한다
     */
    public void progressRound(){
        for(RacingCar car : carList){
            goOrStop(car);
        }
        raceDisplay.showRaceStatus(carList);
    }

    /**
     * 전체 라운드의 경주를 진행한다
     * @param turnCount 진행할 시도횟수
     */
    public void runRace(int turnCount){
        for(int turnIndex = 0; turnIndex < turnCount; turnIndex++){
            progressRound();
        }
    }
}
