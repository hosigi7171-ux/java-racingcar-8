package racingcar.validator;

import static racingcar.constants.CommonConstants.DELIMITER;
import static racingcar.constants.CommonConstants.MAX_NAME_LENGTH;
import static racingcar.constants.CommonConstants.MINUS;
import static racingcar.constants.CommonConstants.PLUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidatorImpl implements InputValidator {

    private void validateNotEmpty(String input) {
        if(input.isEmpty()) throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getText());
    }

    public void validateCarNames(String names) {
        validateNotEmpty(names);

        // 자동차 이름이 5자 이하인지 확인
        List<String> nameArray = new ArrayList<>(Arrays.asList(names.split(DELIMITER)));
        for (String name : nameArray) {
            if (name.length() > MAX_NAME_LENGTH || name.trim().isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_CAR_NAME.getText());
            }
        }
    }

    public void validateTryCount(String tryCountInput) {
        validateNotEmpty(tryCountInput);

        // 음수 또는 + 부호를 붙였는지 확인
        if (tryCountInput.startsWith(PLUS) || tryCountInput.startsWith(MINUS)) {
            throw new IllegalArgumentException(ErrorMessage.TRY_COUNT_WITH_SIGN.getText());
        }

        // 정수인지 확인
        try {
            int tryCountNumber = Integer.parseInt(tryCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.TRY_COUNT_NOT_NUMBER.getText());
        }
    }
}
