package pl.emilfrankiewicz.games.crazynumbers.domain;

import java.util.List;

public class CrazyNumbers {

    int sum(List<Integer> listOfNumbers) {
        return listOfNumbers.stream().mapToInt(number -> number).sum();
    }


}
