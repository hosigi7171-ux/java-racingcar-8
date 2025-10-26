package racingcar.stub;

import racingcar.io.InputReader;

public class StubConsoleInputReader implements InputReader {
    @Override
    public String readCarNames(){
        return "test1,test2";
    }

    @Override
    public String readTryCount(){
        return "3";
    }
}