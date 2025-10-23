package racingcar;

import java.util.List;

public class ResultWriter {
    /**
     * 우승자를 형식에 맞게 출력해주는 메서드
     * @param winnerList 출력할 우승자 리스트
     */
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
