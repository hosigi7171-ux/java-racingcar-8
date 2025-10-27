package racingcar.domain;

import static racingcar.constants.CommonConstants.MOVE_THRESHOLD;

public class RacingCar {
    private final String name;
    private int forwardDistance;

    public RacingCar(String name) {
        this.name = name;
    }

    public RacingCar(String name, int forwardDistance) {
        this.name = name;
        this.forwardDistance = forwardDistance;
    }

    public void moveIfAtLeastFour(int number) {
        if (number >= MOVE_THRESHOLD) {
            forwardDistance++;
        }
    }

    public int getForwardDistance() {
        return forwardDistance;
    }

    public String getName() {
        return name;
    }
}
