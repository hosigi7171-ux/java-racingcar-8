package racingcar.validator;

public interface InputValidator {
    /**
     * 입력받은 자동차 이름들이 유효한 입력인지 확인하는 메서드
     *
     * @param names 입력받은 자동차 이름들 문자열
     */
    void validateCarNames(String names);

    /**
     * 입력받은 시도 횟수가 유효한 입력인지 확인하는 메서드
     *
     * @param tryCountInput 입력받은 시도 횟수 문자열
     */
    void validateTryCount(String tryCountInput);
}