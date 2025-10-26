package racingcar.io;

import java.util.List;
import racingcar.domain.RacingCar;

public class ConsoleResultWriter implements ResultWriter{

    public void printWinner(List<RacingCar> winnerList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("최종 우승자 : ");

        for (int index = 0; index < winnerList.size(); index++) {
            stringBuilder.append(winnerList.get(index).getName());

            if (index != winnerList.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        System.out.println(stringBuilder);
    }
}
