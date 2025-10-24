package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class InputReader {
    private final InputValidator validator;

    public InputReader(){
        validator = new InputValidator();
    }

    public String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNamesString = Console.readLine();

        validator.validateCarNames(carNamesString);

        return carNamesString;
    }

    public String readTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String tryCountString = Console.readLine();

        validator.validateTryCount(tryCountString);

        return tryCountString;
    }
}
