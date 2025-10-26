package racingcar.stub;

import java.util.Iterator;
import java.util.List;
import racingcar.generator.NumberGenerator;

public class SequentialNumberGenerator implements NumberGenerator {
    private final Iterator<Integer> numbers;

    public SequentialNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers.iterator();
    }

    @Override
    public int generate() {
        if (numbers.hasNext()) {
            return numbers.next();
        }

        return 0;
    }
}