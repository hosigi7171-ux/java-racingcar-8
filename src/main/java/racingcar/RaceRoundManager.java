package racingcar;

import java.util.List;
import racingcar.display.RaceDisplay;
import racingcar.generator.NumberGenerator;

public class RaceRoundManager {
    private NumberGenerator numberGenerator;
    private RaceDisplay raceDisplay;
    private List<RacingCar> winnerList;
    private List<RacingCar> carList;

    public RaceRoundManager(NumberGenerator goNumberGenerator) {
    }

    public RaceRoundManager(List<RacingCar> carList, NumberGenerator goNumberGenerator) {
    }

    // startRace
    // 자동차들 전진 또는 정지 시킨다
    public boolean goOrStop(RacingCar car){
        return false;
    }
    // 라운드가 끝날 때마다 현황을 출력한다(또는 StringBuilder 이용)
    // 우승자 리스트는 매 라운드마다 갱신한다
    public void progressRound(){

    }
    // 우승자 리스트를 리턴한다
    public List<RacingCar> runRace(int turnCount){
        return null;
    }

    public List<RacingCar> getWinnerList(){ return winnerList; };
}
