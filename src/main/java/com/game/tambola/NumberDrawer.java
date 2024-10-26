package com.game.tambola;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberDrawer {
    private final Set<Integer> drawnNumbers = new HashSet<>();
    private final Random random = new Random();

    public int draw(int min, int max, int retries) throws IllegalArgumentException {
        validateRange(min, max);

        if (retries > (max - min + 1)) {
            throw new IllegalArgumentException("Could not draw a number");
        }

        int number = random.nextInt(max - min) + min;

        if (drawnNumbers.contains(number)) {
            return draw(min, max, retries + 1);
        } else {
            drawnNumbers.add(number);
            return number;
        }
    }

    private void validateRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Invalid range: min should be less than max");
        }
    }
}
