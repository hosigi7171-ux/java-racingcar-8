package racingcar.validator;

public enum ErrorMessage {
    EMPTY_INPUT("빈 문자열은 입력할 수 없습니다."),
    INVALID_CAR_NAME("자동차 이름은 5자 이하여야 합니다"),
    TRY_COUNT_WITH_SIGN("시도횟수는 부호를 포함할 수 없습니다"),
    TRY_COUNT_NOT_NUMBER("시도횟수는 숫자여야 합니다");

    private final String text;

    ErrorMessage(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}