package racingcar.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidatorImpl implements InputValidator {

    public void validateCarNames(String names) {
        if(names.isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getText());
        }

        // 자동차 이름이 5자 이하인지 확인
        List<String> nameArray = new ArrayList<>(Arrays.asList(names.split(",")));
        for (String name : nameArray) {
            if (name.length() > 5 || name.trim().isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_CAR_NAME.getText());
            }
        }
    }

    public void validateTryCount(String tryCountInput) {
        if(tryCountInput.isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getText());
        }
        // 음수 또는 + 부호를 붙였는지 확인
        if (tryCountInput.startsWith("+") || tryCountInput.startsWith("-")) {
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
