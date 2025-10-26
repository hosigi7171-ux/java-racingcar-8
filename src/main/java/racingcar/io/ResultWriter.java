package racingcar.io;

import java.util.List;
import racingcar.domain.RacingCar;

public interface ResultWriter {

    /**
     * 우승자를 형식에 맞게 출력해주는 메서드
     * @param winnerList 출력할 우승자 리스트
     */
    void printWinner(List<RacingCar> winnerList);
}
