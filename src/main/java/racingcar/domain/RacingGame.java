package racingcar.domain;

import static racingcar.constants.CommonConstants.DELIMITER;

import java.util.ArrayList;
import java.util.List;
import racingcar.io.InputReader;
import racingcar.io.ResultWriter;
import racingcar.service.RaceRoundManager;
import racingcar.validator.InputValidator;

public class RacingGame {
    private final InputReader inputReader;
    private final InputValidator inputValidator;
    private final ResultWriter resultWriter;
    private final RaceRoundManager raceRoundManager;

    public RacingGame(InputReader inputReader, InputValidator inputValidator, ResultWriter resultWriter,
                      RaceRoundManager raceRoundManager) {
        this.inputReader = inputReader;
        this.inputValidator = inputValidator;
        this.resultWriter = resultWriter;
        this.raceRoundManager = raceRoundManager;
    }

    /**
     * 문자열을 받아서 쉼표(,)를 기준으로 분리하는 메서드
     *
     * @param names 입력받은 자동차 이름들 문자열
     * @return 자동차 리스트
     */
    private List<RacingCar> makeCarList(String names) {
        String[] namesArray = names.split(DELIMITER);

        List<RacingCar> cars = new ArrayList<>();
        for (String name : namesArray) {
            cars.add(new RacingCar(name.trim(), 0));
        }

        return cars;
    }

    /**
     * 레이싱 게임(입력+검증+실행+출력)을 실행한다
     */
    public void run() {
        // 이름 입력받기
        String names = inputReader.readCarNames();
        inputValidator.validateCarNames(names);
        List<RacingCar> cars = makeCarList(names);

        // 시도 횟수 입력받기
        String tryCountInput = inputReader.readTryCount();
        inputValidator.validateTryCount(tryCountInput);
        int tryCount = Integer.parseInt(tryCountInput);

        // 경기 진행
        raceRoundManager.runRace(tryCount, cars);

        // 우승자 처리
        List<RacingCar> winners = raceRoundManager.findWinners(cars);
        resultWriter.printWinner(winners);
    }
}
