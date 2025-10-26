package racingcar.io;

import java.util.List;
import racingcar.domain.RacingCar;

public class ConsoleResultWriter implements ResultWriter{

    public void printWinner(List<RacingCar> winnerList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(IOMessage.OUTPUT_WINNER.getText());

        for (int index = 0; index < winnerList.size(); index++) {
            stringBuilder.append(winnerList.get(index).getName());

            if (index != winnerList.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        System.out.println(stringBuilder);
    }
}
