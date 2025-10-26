package racingcar.constants;

public enum RaceTestConstants {
    MOVING_FORWARD(4), // 숫자 생성기용 전진 숫자
    STOP(3), // 숫자 생성기용 정지 숫자
    STOPPED_POSITION(0), // 더미 데이터용 정지일때의 위치
    MOVED_POSITION(1); // 더미 데이터용 전진시 위치

    private final int value;

    RaceTestConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
