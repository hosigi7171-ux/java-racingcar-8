package racingcar.service;

import java.util.List;
import racingcar.domain.RacingCar;

public interface RaceRoundManager {
    /**
     * 자동차를 전진 또는 정지 시킨다
     *
     * @param car 전진 또는 정지시킬 자동차
     */
    void goOrStop(RacingCar car);

    /**
     * 우승자를 찾아주는 메서드
     *
     * @param carList 우승자를 찾을 자동차 리스트
     * @return 우승자 리스트 반환 (동점자도 우승자로 간주)
     */
    List<RacingCar> findWinners(List<RacingCar> carList);

    /**
     * 한 라운드의 경주를 진행한다
     *
     * @param carList 한 라운드를 진행할 자동차 리스트
     */
    void progressRound(List<RacingCar> carList);

    /**
     * 전체 라운드의 경주를 진행한다
     *
     * @param turnCount 진행할 시도횟수
     * @param carList   경주를 진행할 자동차 리스트
     */
    void runRace(int turnCount, List<RacingCar> carList);
}
