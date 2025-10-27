package racingcar.io;

import camp.nextstep.edu.missionutils.Console;
import racingcar.validator.InputValidator;
import racingcar.validator.InputValidatorImpl;

public class ConsoleInputReader implements InputReader{
    private final InputValidator validator;

    public ConsoleInputReader() {
        validator = new InputValidatorImpl();
    }

    public String readCarNames() {
        System.out.println(IOMessage.INPUT_CAR_NAMES.getText());
        String carNamesString = Console.readLine().trim();

        validator.validateCarNames(carNamesString);

        return carNamesString;
    }

    public String readTryCount() {
        System.out.println(IOMessage.INPUT_TRY_COUNT.getText());
        String tryCountString = Console.readLine().trim();

        validator.validateTryCount(tryCountString);

        return tryCountString;
    }
}
