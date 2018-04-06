package pgs.strategies;

import pgs.models.ParityGame;

import java.util.Collections;
import java.util.Random;

public class RandomLiftingStrategy extends InputLiftingStrategy {
    private final Random random;

    public RandomLiftingStrategy(final ParityGame parityGame) {
        this(parityGame, 42);
    }

    public RandomLiftingStrategy(final ParityGame parityGame, long seed) {
        super(parityGame);
        this.random = new Random(seed);
        Collections.shuffle(super.getVertexOrder(), this.random);
    }
}
