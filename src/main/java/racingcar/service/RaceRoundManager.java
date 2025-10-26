package racingcar.service;

import java.util.List;
import racingcar.domain.RacingCar;

public interface RaceRoundManager {
    /**
     * 자동차를 전진 또는 정지 시킨다
     * @param car 전진 또는 정지시킬 자동차
     * @return 전진시 true 리턴, 정지시 false 리턴
     */
    public boolean goOrStop(RacingCar car);

    /**
     * 우승자를 찾아주는 메서드
     * @return 우승자 리스트 반환 (동점자도 우승자로 간주)
     */
    public List<RacingCar> findWinners();

    /**
     * 한 라운드의 경주를 진행한다
     */
    public void progressRound();

    /**
     * 전체 라운드의 경주를 진행한다
     * @param turnCount 진행할 시도횟수
     */
    public void runRace(int turnCount);
}
