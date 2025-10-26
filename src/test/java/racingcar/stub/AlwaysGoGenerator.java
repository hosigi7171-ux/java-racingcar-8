package racingcar.stub;

import racingcar.constants.RaceTestConstants;
import racingcar.generator.NumberGenerator;

public class AlwaysGoGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return RaceTestConstants.MOVING_FORWARD.getValue();
    }
}