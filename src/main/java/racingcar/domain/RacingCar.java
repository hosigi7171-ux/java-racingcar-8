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
        if(number >= 4){
            forwardDistance++;
        }
    }

    public int getForwardDistance() {
        return forwardDistance;
    }

    public void setForwardDistance(int forwardDistance) {
        this.forwardDistance = forwardDistance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
