package racingcar.domain;

public class RacingCar {
    private String name;
    private int forwardDistance;

    public RacingCar(String name) {
        this.name = name;
    }

    public RacingCar(String name, int forwardDistance) {
        this.name = name;
        this.forwardDistance = forwardDistance;
    }

    public void moveIfAtLeastFour(int number) {
        if (number >= 4) {
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
