package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {

    /**
     * 입력받은 자동차 이름들이 유효한 입력인지 확인하는 메서드
     *
     * @param names 입력받은 자동차 이름들 문자열
     */
    public void validateCarNames(String names) {
        List<String> nameArray;
        // 쉼표 기준으로 문자열 split 이 가능한지 확인
        nameArray = new ArrayList<>(Arrays.asList(names.split(",")));

        // 자동차 이름이 5자 이하인지 확인
        for (String name : nameArray) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다");
            }
        }
    }

    /**
     * 입력받은 시도 횟수가 유효한 입력인지 확인하는 메서드
     *
     * @param tryCountInput 입력받은 시도 횟수 문자열
     */
    public void validateTryCount(String tryCountInput) {
        // 음수 또는 + 부호를 붙였는지 확인
        if (tryCountInput.startsWith("+") || tryCountInput.startsWith("-")) {
            throw new IllegalArgumentException("시도횟수는 부호를 포함할 수 없습니다");
        }

        // 정수인지 확인
        try {
            int tryCountNumber = Integer.parseInt(tryCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도횟수는 숫자여야 합니다");
        }
    }
}
