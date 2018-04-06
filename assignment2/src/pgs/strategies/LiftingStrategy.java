package pgs.strategies;

import pgs.models.ParityGame;
import pgs.models.Vertex;

import java.util.Iterator;

public abstract class LiftingStrategy implements Iterator<Vertex> {
    private final ParityGame parityGame;

    public abstract void didLift(Vertex v);
    public abstract void didNotLift(Vertex v);

    public LiftingStrategy(final ParityGame parityGame) {
        this.parityGame = parityGame;
    }

    public ParityGame getParityGame() {
        return this.parityGame;
    }
}
