package racingcar.stub;

import racingcar.constants.RaceTestConstants;
import racingcar.generator.NumberGenerator;

public class StopNumberGenerator implements NumberGenerator {
    @Override
    public int generate() {
        return RaceTestConstants.STOP.getValue();
    }
}
