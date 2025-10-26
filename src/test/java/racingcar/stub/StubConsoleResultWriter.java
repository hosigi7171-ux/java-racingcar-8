package racingcar.stub;

import java.util.List;
import racingcar.domain.RacingCar;
import racingcar.io.IOMessage;
import racingcar.io.ResultWriter;

public class StubConsoleResultWriter implements ResultWriter {
    @Override
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